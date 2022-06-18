package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.LceState
import com.example.hm7_cleanarchitecture.domain.model.PersonDetails
import com.example.hm7_cleanarchitecture.domain.repository.PersonLocalRepository
import com.example.hm7_cleanarchitecture.domain.repository.PersonRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold


class GetPersonDetailsUseCase(
    private val remoteRepository: PersonRemoteRepository,
) {

    suspend operator fun invoke(id: Int): Flow<PersonDetails> =
        flow {
            remoteRepository.getPersonDetails(id = id)
                .fold(
                    onSuccess = {
                        emit(it)
                    },
                    onFailure = {
                        error("Что-то не так !")
                    }
                )
        }
}