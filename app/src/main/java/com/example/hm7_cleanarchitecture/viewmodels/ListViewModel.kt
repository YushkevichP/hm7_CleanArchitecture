package com.example.hm7_cleanarchitecture.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hm7_cleanarchitecture.PersonRepository
import com.example.hm7_cleanarchitecture.model.LceState
import com.example.hm7_cleanarchitecture.model.Person

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class ListViewModel(
    private val personRepository: PersonRepository,
) : ViewModel() {

    private var isLoading = false
    private var currentPage = 1
    private var hasMoreData = true

    private val loadMoreFlow = MutableSharedFlow<LoadState>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST, extraBufferCapacity = 0
    )
    private var isRefreshed = false

    val dataFlow = loadMoreFlow
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
                        Log.d("check", "Подгрузилось из сети , current page $currentPage")

                        //еслм прийдет меньше 20, то это последняя страница и далее не нужно делать onloadMore
                        if (it.size < PAGE_SIZE) {
                            Log.d("check", it.size.toString())

                            hasMoreData = false
                        }
                        LceState.Content(it, hasMoreData)
                    },
                    onFailure = {
                        val cache = (personRepository.getPersonsFromDB(PAGE_SIZE, 0, currentPage))
                        LceState.Content(cache, throwable = it)
                    }
                )
        }
        .onEach {
            personRepository.insertPersons(it.data.map {
                Person(
                    idApi = it.idApi,
                    nameApi = it.nameApi,
                    imageApi = it.imageApi,
                    page = currentPage)
            })
            isLoading = false
            currentPage++
            Log.d("check", "Увеличили страничку только что , current page $currentPage")
        }
        .runningReduce { accumulator, value ->
            if (!isRefreshed) {
                val currentData = accumulator.data + value.data
                LceState.Content(data = currentData, hasMoreData = value.hasMoreData)
            } else value

        }
        .onStart {
            Log.d("check", "Подгрузилось из БД, текущая страница = $currentPage")
            val cache = personRepository.getPersonsFromDB(PAGE_SIZE, 0, currentPage)
            emit(LceState.Content(cache))
        }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            replay = 1
        )

    fun onLoadMore() {

        // проверка на ислоадинг, т.к. порой сразу лишние страницы прогружало
        if (!isLoading && hasMoreData) {
            loadMoreFlow.tryEmit(LoadState.LOAD_MORE)
        }
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
