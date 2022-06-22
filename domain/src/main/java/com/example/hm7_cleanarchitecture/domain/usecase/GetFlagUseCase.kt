package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.Flag
import com.example.hm7_cleanarchitecture.domain.repository.FlagRemoteRepository
import kotlinx.coroutines.flow.flow

class GetFlagUseCase(
    private val remoteRepository: FlagRemoteRepository
) {
    suspend operator fun invoke(name: String) = flow<Flag> {
        remoteRepository.getCountryByName(name)
            .fold(
                onSuccess = {
                            emit(it)
                },
                onFailure = {
                    error(it.message.toString())
                }
            )
    }
}