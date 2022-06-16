package com.example.hm7_cleanarchitecture.data.api


import com.example.hm7_cleanarchitecture.data.model.PersonDetailsDTO
import com.example.hm7_cleanarchitecture.data.model.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://youtu.be/IDVxFjLeecA?t=10822

internal interface PersonApi {

    @GET("character")
    suspend fun getUsersFromApi(
        @Query("page") page: Int,
        ): Response

    @GET("character/{id}")
    suspend fun getPersonDetailsFromApi(
        @Path("id") id: Int,
    ):PersonDetailsDTO

}