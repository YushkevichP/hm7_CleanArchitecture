package com.example.hm7_cleanarchitecture.data.mapper

import com.example.hm7_cleanarchitecture.data.model.CountryResponse
import com.example.hm7_cleanarchitecture.domain.model.Flag

internal fun CountryResponse.toDomainFlag(): Flag{

    return Flag(
        countryName = name.common,
        flagImage = flag
    )
}

