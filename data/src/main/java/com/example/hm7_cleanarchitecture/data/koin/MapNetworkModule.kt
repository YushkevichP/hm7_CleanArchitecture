package com.example.hm7_cleanarchitecture.data.koin



import com.example.hm7_cleanarchitecture.data.api.PersonApi
import com.example.hm7_cleanarchitecture.data.model.CountriesResponseItem
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal val mapNetworkModule = module {

    //create retrofit
    single(qualifier("Countries")) {
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    //create api
    single {
        get<Retrofit>(qualifier("Countries")).create<CountriesResponseItem>()
    }
}