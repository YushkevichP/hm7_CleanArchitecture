package com.example.hm7_cleanarchitecture.koin

import com.example.hm7_cleanarchitecture.PersonRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {

    //create PersonRepository

    singleOf(::PersonRepository)

}


//    single {
//        PersonRepository(get(), get())
//    }  https://youtu.be/aZqDFzYzTzc?t=9609