package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.model.PersonDetails
import com.example.hm7_cleanarchitecture.domain.repository.PersonRepository

//https://youtu.be/aZqDFzYzTzc?t=8612
class GetPersonDetailsUseCase(private val personRepository: PersonRepository) {

    suspend operator fun invoke(id: Int): Result<PersonDetails> {
        return personRepository.getPersonDetails(id = id)
    }

}