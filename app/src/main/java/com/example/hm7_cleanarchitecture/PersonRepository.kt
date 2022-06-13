package com.example.hm7_cleanarchitecture

import com.example.hm7_cleanarchitecture.database.PersonDao
import com.example.hm7_cleanarchitecture.model.Person
import com.example.hm7_cleanarchitecture.retrofit.RickMortyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonRepository(
    private val rickMortyApi: RickMortyApi,
    private val rickMortyDao: PersonDao,
) {

    suspend fun fetchPersons(page: Int) = runCatching {
        withContext(Dispatchers.IO) {
            rickMortyApi.getUsersFromApi(page).results
        }
    }

    suspend fun fetchPersonDetails(id: Int) = runCatching {
        withContext(Dispatchers.IO) {
            rickMortyApi.getPersonDetailsFromApi(id)
        }
    }

    suspend fun getPersonsFromDB(limit: Int, offset: Int, page: Int): List<Person> {
        return rickMortyDao.getSomePersons(limit, offset, page)
    }

    suspend fun insertPersons(list: List<Person>) {
        rickMortyDao.insertPersons(list)
    }

}