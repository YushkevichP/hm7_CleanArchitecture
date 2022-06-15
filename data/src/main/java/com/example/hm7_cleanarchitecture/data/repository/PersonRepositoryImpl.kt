package com.example.hm7_cleanarchitecture.data.repository

import com.example.hm7_cleanarchitecture.data.api.PersonApi
import com.example.hm7_cleanarchitecture.data.mapper.toDomainModels
import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.model.PersonDetails
import com.example.hm7_cleanarchitecture.domain.repository.PersonRepository

class PersonRepositoryImpl(private val personApi: PersonApi) : PersonRepository {

    override suspend fun getPerson(page: Int): Result<List<Person>> {
       return runCatching {
          personApi.getUsersFromApi(page)
       }.map {
           it.toDomainModels()
       }
    }

    override suspend fun getPersonDetails(id: Int): Result<PersonDetails> {
        TODO("Not yet implemented")
    }
}