package com.example.hm7_cleanarchitecture.data.api

import com.example.hm7_cleanarchitecture.data.model.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

        //получаем список всех стран
        @GET("all")
        suspend fun getCountries(): List<CountryResponse>

        @GET("name/{name}")
        suspend fun getCountrtByName(
                @Path("name") name: String
        ): CountryResponse

}