package com.example.feature_database

import com.example.core.tools.general.Country
import com.example.core.tools.general.Genre
import com.example.feature_database.Dao.CountryDao
import com.example.feature_database.Dao.GenreDao

class DataBaseRepository(
    private val genreDao: GenreDao,
    private val countryDao: CountryDao
) {

    fun insertGenre(genre: Genre) = genreDao.insertGenre(genre)

    fun getAllGenre() = genreDao.getAllGenre()

    fun getAllCounter() = countryDao.getAllCounter()

    fun insertCounter(country: Country) = countryDao.insertGenre(country)
}