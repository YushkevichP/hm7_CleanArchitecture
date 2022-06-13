package com.example.hm7_cleanarchitecture.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hm7_cleanarchitecture.model.Person


@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}