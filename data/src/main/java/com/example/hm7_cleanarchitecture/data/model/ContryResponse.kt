package com.example.hm7_cleanarchitecture.data.model

import com.google.gson.annotations.SerializedName


//todo можно заюзать флаг/столицу/ население(?)
internal data class CountryResponse(

    @SerializedName("capital")
    val capital: List<String>,

    @SerializedName("capitalInfo")
    val capitalInfo: CapitalInfo,

    @SerializedName("continents")
    val continents: List<String>,

    @SerializedName("currencies")
    val currencies: Currencies,

    @SerializedName("flag")
    val flag: String,

    @SerializedName("flags")
    val flags: Flags,

    @SerializedName("languages")
    val languages: Languages,

    @SerializedName("latlng")
    val latlng: List<Double>,

    @SerializedName("maps")
    val maps: Maps,

    @SerializedName("name")
    val name: Name,

    @SerializedName("population")
    val population: Int,
)

data class CapitalInfo(
    @SerializedName("latlng")
    val latlng: List<Double>
)

data class Currencies(
    @SerializedName("USD")
    val uSD: USD
)

data class Flags(
    @SerializedName("png")
    val png: String,
    @SerializedName("svg")
    val svg: String
)

data class Languages(
    @SerializedName("eng")
    val eng: String
)

data class Maps(
    @SerializedName("googleMaps")
    val googleMaps: String,
    @SerializedName("openStreetMaps")
    val openStreetMaps: String
)

data class Name(
    @SerializedName("common")
    val common: String,
    @SerializedName("nativeName")
    val nativeName: NativeName,
    @SerializedName("official")
    val official: String
)

data class USD(
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)

data class NativeName(
    @SerializedName("eng")
    val eng: EngX
)

data class EngX(
    @SerializedName("common")
    val common: String,
    @SerializedName("official")
    val official: String
)




