package com.example.feature_database.repository

import android.util.Log
import androidx.room.Update
import com.example.feature_database.Dao.FilmsCollectionDao
import com.example.feature_database.Dao.FilmsDao
import com.example.feature_database.entity.FilmEntity
import com.example.feature_database.entity.FilmsCollectionEntity
import com.example.feature_database.entity.MergeCollectionAndFilms

class CollectionsFilmsRepository(
    private val filmsDao: FilmsDao,
    private val filmsCollectionDao: FilmsCollectionDao
) {

    fun insertFilm(filmEntity: FilmEntity, collectionEntity: FilmsCollectionEntity) {
        collectionEntity.size++
        filmsDao.insertFilm(filmEntity)
        val isSuccess = filmsCollectionDao
            .insertMergeCollectionAndFilms(
                MergeCollectionAndFilms(collectionEntity.id!!, filmEntity.filmId)
            )
        if (isSuccess!=-1L) filmsCollectionDao.updateCollection(collectionEntity)
    }

    fun getFilmFromID(id: Int) = filmsDao.getFilmById(id)

    fun addCollection(name: String) =
        filmsCollectionDao.insertCollection(FilmsCollectionEntity(nameCollection = name))


    fun addFilmToCollection(filmId: Int, collectionID: Int) =
        filmsCollectionDao
            .insertMergeCollectionAndFilms(MergeCollectionAndFilms(collectionID, filmId))

    fun getFilmsFromCollectionID(collectionID: Int) =
        filmsCollectionDao.getFilmsFromCollectionID(collectionID)

    fun getAllCollection() = filmsCollectionDao.getCollection()


}