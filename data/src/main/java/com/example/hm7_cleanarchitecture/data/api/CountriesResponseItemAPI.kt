package com.example.hm7_cleanarchitecture.data.api

import com.example.hm7_cleanarchitecture.data.model.CountriesResponseItem
import retrofit2.http.GET

interface CountriesResponseItemAPI {

        @GET("all")
        suspend fun getCountries(): List<CountriesResponseItem>

}