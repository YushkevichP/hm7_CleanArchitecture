package com.example.hm7_cleanarchitecture.koin

import com.example.hm7_cleanarchitecture.PersonRepository
import org.koin.dsl.module

val repositoryModule = module {

    //create PersonRepository
    single {
        PersonRepository(get(), get())
    }
}