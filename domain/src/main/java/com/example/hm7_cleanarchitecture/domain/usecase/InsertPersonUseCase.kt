package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.repository.PersonLocalRepository

class InsertPersonUseCase(
    private val personLocalRepository: PersonLocalRepository,
) {
    suspend operator fun invoke(list: List<Person>) {
        return personLocalRepository.insertPersons(list)
    }
}