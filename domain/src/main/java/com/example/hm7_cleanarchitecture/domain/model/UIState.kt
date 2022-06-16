package com.example.hm7_cleanarchitecture.domain.model

data class UIState(
    val persons: List<Person> = emptyList(),
    val throwable: Throwable? = null,
    val isLoading : Boolean = true
)