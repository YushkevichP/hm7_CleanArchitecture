package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.repository.PersonRepository

class GetPersonUseCase(private val personRepository: PersonRepository) {

    suspend operator fun invoke(page:Int) : Result<List<Person>>{
        return personRepository.getPerson(page = page)
    }
}