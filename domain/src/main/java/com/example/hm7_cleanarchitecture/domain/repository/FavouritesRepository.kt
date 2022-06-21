package com.example.hm7_cleanarchitecture.domain.repository

import com.example.hm7_cleanarchitecture.domain.model.Person

interface FavouritesRepository {

    suspend fun getFavourites(limit: Int, page: Int) : List<Person>
    suspend fun insertFavourites(list: List<Person>, page: Int)
    suspend fun removeFavourites(person:Person)
}