package com.example.hm7_cleanarchitecture.data.repository

import com.example.hm7_cleanarchitecture.data.database.PersonDao
import com.example.hm7_cleanarchitecture.data.mapper.toDomainModel
import com.example.hm7_cleanarchitecture.data.mapper.toDomainModels
import com.example.hm7_cleanarchitecture.data.model.PersonEntity
import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.model.PersonDetails
import com.example.hm7_cleanarchitecture.domain.repository.PersonLocalRepository
import com.example.hm7_cleanarchitecture.domain.repository.PersonRemoteRepository

internal class PersonLocalRepositoryImpl(
    private val personDao: PersonDao,
) : PersonLocalRepository {

    override suspend fun getPersonsFromDB(
        limit: Int,
        offset: Int,
        page: Int,
    ): Result<List<Person>> {
        return runCatching {
            personDao.getSomePersons(limit, offset, page)
        }.map {
            it.map {
                it.toDomainModel()
            }
        }
    }

    override suspend fun insertPersons(list: List<Person>) {
        runCatching {
            personDao.insertPersons(list)
        }
    }
}




//    override suspend fun getPerson(page: Int): Result<List<Person>> {
//        return runCatching {
//            personDao.getSomePersons(page, )
//        }.map { personEntities->
//            personEntities.map {
//                it.toDomainModel()
//            }
//        }
//    }