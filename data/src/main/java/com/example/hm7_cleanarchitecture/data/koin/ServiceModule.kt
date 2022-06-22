package com.example.hm7_cleanarchitecture.data.koin
import com.example.hm7_cleanarchitecture.data.service.LocationService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val serviceModule = module {
    singleOf(::LocationService)
}