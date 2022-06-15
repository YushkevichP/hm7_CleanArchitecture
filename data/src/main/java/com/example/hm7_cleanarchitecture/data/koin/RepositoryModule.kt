package com.example.hm7_cleanarchitecture.data.koin

import com.example.hm7_cleanarchitecture.data.repository.PersonRepositoryImpl
import com.example.hm7_cleanarchitecture.domain.repository.PersonRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {

    //create PersonRepository // передаем по интерфейсу https://youtu.be/aZqDFzYzTzc?t=9463
    // можно так (передаем по интерфейсу)
//    single<PersonRepository> {
//        PersonRepositoryImpl(get())
//    }
    // можно так
    singleOf(::PersonRepositoryImpl) { bind<PersonRepository>() }

}