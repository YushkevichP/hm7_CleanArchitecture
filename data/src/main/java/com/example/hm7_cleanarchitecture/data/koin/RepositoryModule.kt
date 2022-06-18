package com.example.hm7_cleanarchitecture.data.koin

import com.example.hm7_cleanarchitecture.data.repository.PersonLocalRepositoryImpl
import com.example.hm7_cleanarchitecture.data.repository.PersonRemoteRepositoryImpl
import com.example.hm7_cleanarchitecture.domain.repository.PersonLocalRepository
import com.example.hm7_cleanarchitecture.domain.repository.PersonRemoteRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {

    singleOf(::PersonRemoteRepositoryImpl) {
        bind<PersonRemoteRepository>()
    }

    singleOf(::PersonLocalRepositoryImpl) {
        bind<PersonLocalRepository>()
    }
}