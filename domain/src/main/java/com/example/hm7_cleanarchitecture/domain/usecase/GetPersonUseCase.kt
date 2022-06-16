package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.LceState
import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.repository.PersonLocalRepository
import com.example.hm7_cleanarchitecture.domain.repository.PersonRemoteRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class GetPersonUseCase(
    private val remoteRepository: PersonRemoteRepository,
    private val localRepository: PersonLocalRepository,
) {

    operator fun invoke(page: Int): Flow<LceState<List<Person>>> =
        flow {
            remoteRepository.getPerson(page)
                .fold(
                    onSuccess = { list ->
                        localRepository.insertPersons(list)
                        emit(LceState.Content(list))
                    },
                    onFailure = {
                        emit(LceState.Error(it))
                    }
                )
        }.onStart {
            emit(LceState.Loading)
        }
}








