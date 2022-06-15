package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.repository.PersonLocalRepository
import com.example.hm7_cleanarchitecture.domain.repository.PersonRemoteRepository

class GetPersonUseCase(
    private val remoteRepository: PersonRemoteRepository,
    private val localRepository: PersonLocalRepository
    ) {

    suspend operator fun invoke(page:Int) : Result<List<Person>>{
        return remoteRepository.getPerson(page = page)
    }
}