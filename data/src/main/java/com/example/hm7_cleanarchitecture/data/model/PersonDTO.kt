package com.example.hm7_cleanarchitecture.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// серверная сущность
internal data class PersonDTO(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val namePerson: String,

    @SerializedName("image")
    val imageUrl: String,

    val page: Int,

    )
