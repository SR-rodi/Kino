package com.example.feature_database

import android.content.Context
import androidx.room.*
import androidx.room.Database
import com.example.core.tools.general.Country
import com.example.core.tools.general.Genre
import com.example.feature_database.Dao.CountryDao
import com.example.feature_database.Dao.GenreDao

@Database(
    entities = [
        Genre::class,
        Country::class
    ], version = 1
)
abstract class CinemaDatabase() : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    abstract fun genreDao(): GenreDao
}



