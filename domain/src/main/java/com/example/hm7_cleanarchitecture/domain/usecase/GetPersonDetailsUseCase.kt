package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.PersonDetails
import com.example.hm7_cleanarchitecture.domain.repository.PersonLocalRepository
import com.example.hm7_cleanarchitecture.domain.repository.PersonRemoteRepository

//https://youtu.be/aZqDFzYzTzc?t=8612
class GetPersonDetailsUseCase(
    private val personRemoteRepository: PersonRemoteRepository,
    private val personLocalRepository: PersonLocalRepository
    ) {

    suspend operator fun invoke(id: Int): Result<PersonDetails> {
        return personRemoteRepository.getPersonDetails(id = id)
    }

}