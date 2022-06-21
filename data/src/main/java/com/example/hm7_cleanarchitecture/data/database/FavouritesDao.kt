package com.example.hm7_cleanarchitecture.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hm7_cleanarchitecture.data.model.FavouriteEntity
import com.example.hm7_cleanarchitecture.data.model.PersonEntity

@Dao
internal interface FavouritesDao {

    @Query("SELECT * FROM personentity WHERE (:page) LIKE page LIMIT :limit")
    suspend fun getFavourites(limit: Int, page: Int): List<FavouriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(list: List<FavouriteEntity>)

    @Query("DELETE FROM FavouriteEntity")
    suspend fun removeFavourite(person: FavouriteEntity)

}