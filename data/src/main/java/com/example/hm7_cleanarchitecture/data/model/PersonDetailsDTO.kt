package com.example.hm7_cleanarchitecture.data.model

import com.google.gson.annotations.SerializedName

internal data class PersonDetailsDTO(

    val id: Int,
    val name: String,
    @SerializedName("image")
    val avatarApiDetails: String,
    val gender: String,
    val status: String,
)