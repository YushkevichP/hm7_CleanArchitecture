package com.example.hm7_cleanarchitecture.data.mapper

import com.example.hm7_cleanarchitecture.data.model.PersonDetailsDTO
import com.example.hm7_cleanarchitecture.domain.model.PersonDetails


fun List<PersonDetailsDTO>.toDomainModel(): List<PersonDetails> {
    return map {
        it.toDomainModel()
    }
}

fun PersonDetailsDTO.toDomainModel(): PersonDetails {
    return PersonDetails(
        id = id,
        name = name,
        avatarApiDetails = avatarApiDetails,
        gender = gender,
        status = status
    )
}