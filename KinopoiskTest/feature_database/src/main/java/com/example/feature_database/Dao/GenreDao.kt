package com.example.feature_database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.tools.general.Genre
@Dao
interface GenreDao{

    @Insert
    fun insertGenre(genre: Genre)

    @Query("SELECT*FROM genre WHERE info =:name")
    fun getGenreByName(name:String):Genre?


    @Query("SELECT*FROM genre WHERE id =:genreID")
    fun getGenreById(genreID:Int):Genre?

    @Query("SELECT*FROM genre")
    fun getAllGenre():List<Genre>
}