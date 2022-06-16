package com.example.hm7_cleanarchitecture.domain.model

sealed class LceState<out T> {

    object Loading : LceState<Nothing>()

    data class Content<T>(
        val data: T,
        val hasMoreData: Boolean = true, // чтоб остановить запросы, когда подгрузится последняя страничка
        val throwable: Throwable? = null,
    ) : LceState<T>()

    data class Error(val throwable: Throwable) : LceState<Nothing>()
}