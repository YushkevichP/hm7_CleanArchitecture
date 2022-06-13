package com.example.hm7_cleanarchitecture.retrofit


import com.example.hm7_cleanarchitecture.model.PersonDetails
import com.example.hm7_cleanarchitecture.model.WrapperForListFromApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://youtu.be/IDVxFjLeecA?t=10822

interface RickMortyApi {

    @GET("character")
    suspend fun getUsersFromApi(
        @Query("page") page: Int,
        ): WrapperForListFromApi

    @GET("character/{id}")
    suspend fun getPersonDetailsFromApi(
        @Path("id") id: Int, // Path -подставление значения в какой-то запрос.
    ): PersonDetails

}