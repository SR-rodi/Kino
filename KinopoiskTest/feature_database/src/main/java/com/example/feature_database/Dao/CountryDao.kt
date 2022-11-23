package com.example.feature_database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.tools.general.Country
@Dao
interface CountryDao{

    @Insert
    fun insertGenre(country: Country)

    @Query("SELECT*FROM country WHERE info =:name")
    fun getCounterByName(name:String) :Country?


    @Query("SELECT*FROM country WHERE id =:countryID")
    fun getCounterById(countryID: Int):Country?

    @Query("SELECT*FROM country")
    fun getAllCounter():List<Country>
}