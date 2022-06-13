package com.example.hm7_cleanarchitecture.database

import android.app.Application
import com.example.hm7_cleanarchitecture.koin.databaseModule
import com.example.hm7_cleanarchitecture.koin.networkModule
import com.example.hm7_cleanarchitecture.koin.repositoryModule
import com.example.hm7_cleanarchitecture.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Hm7CleanArchitecture : Application() {

    override fun onCreate() {
        super.onCreate()

        //начало работы с koin.
        startKoin {
            androidContext(this@Hm7CleanArchitecture)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}
