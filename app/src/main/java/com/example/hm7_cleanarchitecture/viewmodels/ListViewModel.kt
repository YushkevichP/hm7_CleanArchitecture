package com.example.hm7_cleanarchitecture.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hm7_cleanarchitecture.domain.model.LceState
import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.model.UIState
import com.example.hm7_cleanarchitecture.domain.usecase.GetPersonUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class ListViewModel(
    private val getPersonUseCase: GetPersonUseCase,
) : ViewModel() {


    private var isLoading = false
    private var currentPage = 1
    private var hasMoreData = true
    private var isRefreshed = false
    private var list = listOf<Person>()

    private val loadMoreFlow = MutableSharedFlow<LoadState>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val dataFlow = loadMoreFlow
        .onEach {
            isLoading = true
            if (it == LoadState.REFRESH) {
                isRefreshed = true
                list = emptyList()
                currentPage = 1
            } else isRefreshed = false
        }
        .flatMapLatest {
            getPersonUseCase(currentPage)
        }
        .runningFold(UIState()) {state, lce ->

            when (lce) {
                is LceState.Content -> {
                    list = list + lce.data
                    currentPage++
                    state.copy(persons = list, hasMoreData = lce.hasMoreData, throwable = null)
                }

                is LceState.Error -> {
                    state.copy(throwable = lce.throwable)
                }

                is LceState.Loading -> { // cach
                    state.copy(persons = lce.data)
                }
            }
        }.onEach {
            isLoading = false
        }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            replay = 1
        )

    init {
        onLoadMore()
    }

    fun onLoadMore() {
        if (!isLoading && hasMoreData) {
            loadMoreFlow.tryEmit(LoadState.LOAD_MORE)
        }
    }

    fun onRefresh() {
        loadMoreFlow.tryEmit(LoadState.REFRESH)
    }

    enum class LoadState {
        LOAD_MORE, REFRESH
    }

    companion object {
        private const val PAGE_SIZE = 20
    }

}


//    private var isLoading = false
//    private var currentPage = 1
//    private var hasMoreData = true
//    private var isRefreshed = false
//
//    private val loadMoreFlow = MutableSharedFlow<LoadState>(
//        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST, extraBufferCapacity = 0
//    )
//
//
//    val dataFlow = loadMoreFlow
//        .onEach {
//            isLoading = true
//            if (it == LoadState.REFRESH) {
//                isRefreshed = true
//                currentPage = 1
//            } else isRefreshed = false
//        }
//        .map {
//
//            personRepository.fetchPersons(currentPage)
//                .fold(
//                    onSuccess = {
//                        Log.d("checkMyApp", "Подгрузилось из сети , current page $currentPage")
//
//                        //еслм прийдет меньше 20, то это последняя страница и далее не нужно делать onloadMore
//                        if (it.size < PAGE_SIZE) {
//                            Log.d("checkMyApp", it.size.toString())
//
//                            hasMoreData = false
//                        }
//                        LceState.Content(it, hasMoreData)
//                    },
//                    onFailure = {
//                        val cache = (personRepository.getPersonsFromDB(PAGE_SIZE, 0, currentPage))
//                        LceState.Content(cache, throwable = it)
//                    }
//                )
//        }
//        .onEach {
//            personRepository.insertPersons(it.data.map {
//                Person(
//                    idApi = it.idApi,
//                    nameApi = it.nameApi,
//                    imageApi = it.imageApi,
//                    page = currentPage)
//            })
//            isLoading = false
//            currentPage++
//            Log.d("checkMyApp", "Увеличили страничку только что , current page $currentPage")
//        }
//        .runningReduce { accumulator, value ->
//            if (!isRefreshed) {
//                val currentData = accumulator.data + value.data
//                LceState.Content(data = currentData, hasMoreData = value.hasMoreData)
//            } else value
//
//        }
//        .onStart {
//            Log.d("checkMyApp", "Подгрузилось из БД, текущая страница = $currentPage")
//            val cache = personRepository.getPersonsFromDB(PAGE_SIZE, 0, currentPage)
//            emit(LceState.Content(cache))
//        }
//        .shareIn(
//            scope = viewModelScope,
//            started = SharingStarted.Eagerly,
//            replay = 1
//        )
//
//    fun onLoadMore() {
//
//        // проверка на ислоадинг, т.к. порой сразу лишние страницы прогружало
//        if (!isLoading && hasMoreData) {
//            loadMoreFlow.tryEmit(LoadState.LOAD_MORE)
//        }
//    }
//

//
//    init {
//        onLoadMore()
//    }
//
//    companion object {
//        private const val PAGE_SIZE = 20
//    }
//
//    enum class LoadState {
//        LOAD_MORE, REFRESH
//    }
//}
