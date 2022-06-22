package com.example.hm7_cleanarchitecture.data.repository

import com.example.hm7_cleanarchitecture.data.api.CountryApi
import com.example.hm7_cleanarchitecture.data.mapper.toDomainModel
import com.example.hm7_cleanarchitecture.domain.model.Country
import com.example.hm7_cleanarchitecture.domain.repository.CountryRemoteRepository

 internal class CountryRemoteRepositoryImpl(
     private val countryApi: CountryApi,
) : CountryRemoteRepository {

    override suspend fun getCountries() =
        runCatching {
            countryApi.getCountries().map { item ->
                Country(
                    name = item.name.common,
                    longitude = item.latlng.getOrNull(1) ?: 0.0,
                    latitude = item.latlng.getOrNull(0) ?: 0.0,
                    flag = item.flags.png
                )
            }
        }
}


//override suspend fun getCountries(): Result<List<Country>> {
//    return runCatching {
//        countryApi.getCountries().toDomainModel()
//    }