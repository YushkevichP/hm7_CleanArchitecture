package com.example.hm7_cleanarchitecture.data.mapper

import com.example.hm7_cleanarchitecture.data.model.PersonDTO
import com.example.hm7_cleanarchitecture.data.model.PersonEntity
import com.example.hm7_cleanarchitecture.domain.model.Person

//два конвертера для перегона персонДТО и Ентити в персон

fun List<PersonDTO>.toDomainModels(): List<Person>{
    return map {
        it.toDomainModel()
    }
}

fun PersonDTO.toDomainModel(): Person {
    return Person(
        id = id,
        name = namePerson,
        imageUrl = imageUrl,
        page = page
    )
}

//fun List<PersonEntity>.toDomainModels(): List<Person>{
//    return map {
//        it.toDomainModel()
//    }
//}

fun PersonEntity.toDomainModel(): Person {
    return Person(
        id = id,
        name = name,
        imageUrl = imageUrl,
        page = page
    )
}