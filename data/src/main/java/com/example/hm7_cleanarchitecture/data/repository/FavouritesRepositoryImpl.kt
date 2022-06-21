package com.example.hm7_cleanarchitecture.data.repository

import com.example.hm7_cleanarchitecture.data.database.FavouritesDao
import com.example.hm7_cleanarchitecture.data.mapper.toDomainModel
import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.repository.FavouritesRepository

internal class FavouritesRepositoryImpl(
    private val favouritesDao: FavouritesDao
) :FavouritesRepository {

    override suspend fun getFavourites(
        limit: Int, page: Int
    ): List<Person> {
       return favouritesDao.getFavourites(limit,page)
           .map {
           it.toDomainModel()
       }
    }

    override suspend fun insertFavourites(list: List<Person>, page: Int) {
        favouritesDao.addFavourite(list.map { it.toDomainModel() })
    }

    override suspend fun removeFavourites(person: Person) {
        favouritesDao.removeFavourite(person.toDomainModel())
    }
}