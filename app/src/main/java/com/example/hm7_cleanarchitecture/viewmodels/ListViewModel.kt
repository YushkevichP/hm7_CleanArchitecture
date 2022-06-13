package com.example.hm7_cleanarchitecture.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hm7_cleanarchitecture.PersonRepository
import com.example.hm7_cleanarchitecture.model.Person

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class ListViewModel(
    private val personRepository: PersonRepository,
) : ViewModel() {

    private var isLoading = false
    private var currentPage = 1
    private val loadMoreFlow = MutableSharedFlow<LoadState>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST, extraBufferCapacity = 0
    )
    private var isRefreshed = false

    val dataFlow = loadMoreFlow
        .filter { !isLoading }
        .onEach {
            isLoading = true
            if (it == LoadState.REFRESH) {
                isRefreshed = true
                currentPage = 1
            } else isRefreshed = false
        }
        .map {
            delay(1000)
            personRepository.fetchPersons(currentPage)
                .fold(
                    onSuccess = {
                        Log.d("INIT", "Подгрузилось из сети , current page $currentPage")
                        it
                    },
                    onFailure = {
                        (personRepository.getPersonsFromDB(PAGE_SIZE, 0, currentPage))
                    }
                )
        }
        .onEach {
            personRepository.insertPersons(it.map {
                Person(
                    idApi = it.idApi,
                    nameApi = it.nameApi,
                    imageApi = it.imageApi,
                    page = currentPage)
            })
            isLoading = false
            currentPage++
            Log.d("INIT", "Увеличили страничку только что , current page $currentPage")
        }
        .runningReduce { accumulator, value ->
            if (!isRefreshed) {
                accumulator + value
            } else value

        }
        .onStart {
            Log.d("INIT", "Подгрузилось из бд page size is $PAGE_SIZE, current page $currentPage")
            emit(personRepository.getPersonsFromDB(PAGE_SIZE, 0, currentPage))

        }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            replay = 1
        )

    fun onLoadMore() {
        loadMoreFlow.tryEmit(LoadState.LOAD_MORE)
    }

    fun onRefresh() {
        loadMoreFlow.tryEmit(LoadState.REFRESH)
    }

    init {
        onLoadMore()
    }

    companion object {
        private const val PAGE_SIZE = 3
    }

    enum class LoadState {
        LOAD_MORE, REFRESH
    }
}

//todo странно работает паджинация, грузит сразу по 2 странички, нужно разобраться