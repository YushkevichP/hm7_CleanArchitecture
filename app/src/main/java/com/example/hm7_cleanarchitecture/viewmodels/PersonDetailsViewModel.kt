package com.example.hm7_cleanarchitecture.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hm7_cleanarchitecture.PersonRepository
import kotlinx.coroutines.flow.*

class PersonDetailsViewModel(
    private val id: Int,
    private val personRepository: PersonRepository,
) : ViewModel() {

    val dataFlow = flow {
        emit(personRepository.fetchPersonDetails(id = id)
        )
    }
        .shareIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        replay = 1
    )


//    suspend fun fetchDetails(id: Int): Flow<PersonDetails>? {
//        return personRepository.fetchPersonDetails(id)
//            .map {
//                flowOf(it)
//            }.getOrNull()
}
