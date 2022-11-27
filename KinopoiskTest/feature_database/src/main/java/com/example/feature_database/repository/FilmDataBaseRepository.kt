package com.example.feature_database.repository

import com.example.feature_database.Dao.FilmsCollectionDao
import com.example.feature_database.Dao.FilmsDao
import com.example.feature_database.entity.FilmEntity

class FilmDataBaseRepository(
    private val filmsDao: FilmsDao,
    private val filmsCollectionDao: FilmsCollectionDao
) {

    fun getFilmByID(filmID: Int) = filmsDao.getFilmById(filmID)

    fun insertFilm(filmEntity: FilmEntity) = filmsDao.insertFilm(filmEntity)

    fun updateFilm(filmEntity: FilmEntity) = filmsDao.updateFilm(filmEntity)

    fun deleteFilm(filmEntity: FilmEntity) = filmsDao.deleteFilm(filmEntity)


}