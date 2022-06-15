package com.example.hm7_cleanarchitecture.data.mapper

import com.example.hm7_cleanarchitecture.data.model.PersonDTO
import com.example.hm7_cleanarchitecture.data.model.PersonDetailsDTO
import com.example.hm7_cleanarchitecture.data.model.PersonEntity
import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.model.PersonDetails

//два конвертера для перегона персонДТО и Ентити в персон

internal fun List<PersonDTO>.toDomainModels(): List<Person>{
    return map {
        it.toDomainModel()
    }
}

internal fun PersonDTO.toDomainModel(): Person {
    return Person(
        id = id,
        name = namePerson,
        imageUrl = imageUrl,
        page = page
    )
}

internal fun PersonEntity.toDomainModel(): Person {
    return Person(
        id = id,
        name = name,
        imageUrl = imageUrl,
        page = page
    )
}

//для инсерта в бд хотело сделать что0то такое
//@JvmName("toDomainModelsPersonEntity")
//internal fun List<PersonEntity>.toDomainModels(): List<Person>{
//    return map {
//        it.toDomainModel()
//    }
//}


//fun List<PersonEntity>.toDomainModels(): List<Person>{
//    return map {
//        it.toDomainModel()
//    }
//}