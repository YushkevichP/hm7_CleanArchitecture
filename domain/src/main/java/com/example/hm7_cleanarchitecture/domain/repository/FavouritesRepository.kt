package com.example.hm7_cleanarchitecture.domain.repository

import com.example.hm7_cleanarchitecture.domain.model.Person

interface FavouritesRepository {

    suspend fun getFavourites(limit: Int) : List<Person>
    suspend fun insertFavourites(person: Person)
//    suspend fun removeFavourites(person:Person)
}