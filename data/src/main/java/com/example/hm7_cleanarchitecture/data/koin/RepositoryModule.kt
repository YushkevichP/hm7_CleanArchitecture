package com.example.hm7_cleanarchitecture.data.koin

import com.example.hm7_cleanarchitecture.data.repository.PersonRepositoryImpl
import com.example.hm7_cleanarchitecture.domain.repository.PersonRepository
import org.koin.dsl.module

val repositoryModule = module {

    //create PersonRepository // передаем по интерфейсу https://youtu.be/aZqDFzYzTzc?t=9463
    single<PersonRepository> {
        PersonRepositoryImpl(get())
    }
}