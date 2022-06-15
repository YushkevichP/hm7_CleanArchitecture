package com.example.hm7_cleanarchitecture.data.api


import com.example.hm7_cleanarchitecture.data.model.PersonDTO

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://youtu.be/IDVxFjLeecA?t=10822

interface PersonApi {

    @GET("character")
    suspend fun getUsersFromApi(
        @Query("page") page: Int,
        ): List<PersonDTO>

//    @GET("character/{id}")
//    suspend fun getPersonDetailsFromApi(
//        @Path("id") id: Int, // Path -подставление значения в какой-то запрос.
//    ):PersonDetails

}