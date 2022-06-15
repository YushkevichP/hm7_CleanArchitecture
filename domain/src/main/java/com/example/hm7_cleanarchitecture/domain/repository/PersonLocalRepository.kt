package com.example.hm7_cleanarchitecture.domain.repository

import com.example.hm7_cleanarchitecture.domain.model.Person

interface PersonLocalRepository {

    suspend fun getPersonsFromDB(limit: Int, offset: Int, page: Int) : Result<List<Person>>
    suspend fun insertPersons(list: List<Person>)
}