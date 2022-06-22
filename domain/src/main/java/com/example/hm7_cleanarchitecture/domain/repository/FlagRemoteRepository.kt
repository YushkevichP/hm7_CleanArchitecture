package com.example.hm7_cleanarchitecture.domain.repository

import com.example.hm7_cleanarchitecture.domain.model.Flag

interface FlagRemoteRepository {

    suspend fun getCountryByName(name:String): Result<Flag>
}