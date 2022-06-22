package com.example.hm7_cleanarchitecture.data.repository

import com.example.hm7_cleanarchitecture.data.api.CountryApi
import com.example.hm7_cleanarchitecture.data.mapper.toDomainFlag
import com.example.hm7_cleanarchitecture.domain.model.Flag
import com.example.hm7_cleanarchitecture.domain.repository.FlagRemoteRepository

internal class FlagRemoteRepositoryImpl(
    private val countryApi: CountryApi,
) : FlagRemoteRepository {

    override suspend fun getCountryByName(name: String): Result<Flag> {

        return runCatching {
            val country = countryApi.getCountrtByName(name)
            country.toDomainFlag()
        }
    }
}

//мапил сразу тут так - но вынес в мапперы, там иначе
//override suspend fun getCountryByName(name:String): Result<Flag> {
//    return runCatching {
//        val country =   countryApi.getCountrtByName(name)
//        Flag(
//            countryName = country.get(0).name.common,
//            flagImage = country.get(0).flag
//        )
//    }
//}
//}