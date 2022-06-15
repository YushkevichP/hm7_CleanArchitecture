package com.example.hm7_cleanarchitecture.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hm7_cleanarchitecture.domain.repository.PersonRemoteRepository
import kotlinx.coroutines.flow.*

class PersonDetailsViewModel(
    private val id: Int,
    private val personRemoteRepository: PersonRemoteRepository,
) : ViewModel() {

    val dataFlow = flow {
        emit(personRemoteRepository.getPersonDetails(id = id)
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
