package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.Country
import com.example.hm7_cleanarchitecture.domain.repository.CountryRemoteRepository
import kotlinx.coroutines.flow.flow

class GetCountryUseCase(
    private val remoteRepository: CountryRemoteRepository,
) {
    suspend operator fun invoke() = flow<List<Country>> {
        remoteRepository.getCountries()
            .fold(
                onSuccess = {
                    emit(it)
                },
                onFailure = {
                    emit(emptyList())
                }
            )
    }
}