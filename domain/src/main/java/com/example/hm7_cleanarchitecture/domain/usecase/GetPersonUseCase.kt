package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.repository.PersonLocalRepository
import com.example.hm7_cleanarchitecture.domain.repository.PersonRemoteRepository

class GetPersonUseCase(
    private val personRemoteRepository: PersonRemoteRepository,
    private val personLocalRepository: PersonLocalRepository
    ) {

    suspend operator fun invoke(page:Int) : Result<List<Person>>{
        return personRemoteRepository.getPerson(page = page)
    }
}