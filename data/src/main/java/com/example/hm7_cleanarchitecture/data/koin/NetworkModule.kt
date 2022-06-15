package com.example.hm7_cleanarchitecture.data.koin

import com.example.hm7_cleanarchitecture.data.api.PersonApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    //create retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()) // преобразует json объекты в наши оюъекты
            .client(get())
            .build()
    }

    //create api
    single {
        get<Retrofit>().create<PersonApi>()
    }
}