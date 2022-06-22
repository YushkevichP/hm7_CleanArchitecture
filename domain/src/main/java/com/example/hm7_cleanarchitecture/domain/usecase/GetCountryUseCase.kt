package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.Country
import com.example.hm7_cleanarchitecture.domain.model.LceState
import com.example.hm7_cleanarchitecture.domain.model.PersonDetails
import com.example.hm7_cleanarchitecture.domain.repository.CountryRemoteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCountryUseCase(
    private val remoteRepository: CountryRemoteRepository,
) {
suspend operator fun invoke() : Flow<LceState<List<Country>>> =
    flow {
        remoteRepository.getCountries()
            .fold(
                onSuccess = {
                    emit(LceState.Content(it))
                },
                onFailure = {
                    emit(LceState.Error(it))

                }
            )
    }
}



