package com.example.hm7_cleanarchitecture.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hm7_cleanarchitecture.data.model.PersonDTO
import com.example.hm7_cleanarchitecture.data.model.PersonEntity
import com.example.hm7_cleanarchitecture.domain.model.Person


@Dao
internal interface PersonDao {

    @Query("SELECT * FROM personentity WHERE (:page) LIKE page LIMIT :limit OFFSET :offset")
    suspend fun getSomePersons(limit: Int, offset: Int, page: Int): List<PersonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersons(list: List<PersonEntity>)

}