package com.example.hm7_cleanarchitecture.koin
import com.example.hm7_cleanarchitecture.googlemap.LocationService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val serviceModule = module {
    singleOf(::LocationService)
}