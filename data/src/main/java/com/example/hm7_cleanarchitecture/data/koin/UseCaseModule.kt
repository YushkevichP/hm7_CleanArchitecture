package com.example.hm7_cleanarchitecture.data.koin

import com.example.hm7_cleanarchitecture.domain.usecase.GetPersonDetailsUseCase
import com.example.hm7_cleanarchitecture.domain.usecase.GetPersonUseCase

import org.koin.core.module.dsl.factoryOf

import org.koin.dsl.module

internal val useCaseModule = module {
    factory { GetPersonUseCase(get(), get()) }
    factory { GetPersonDetailsUseCase(get())}

}
