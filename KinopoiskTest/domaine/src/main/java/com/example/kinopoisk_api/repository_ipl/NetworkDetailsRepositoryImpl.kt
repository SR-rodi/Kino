package com.example.kinopoisk_api.repository_ipl

import com.example.kinopoisk_api.api.DetailFilmsApi
import com.example.kinopoisk_api.repo.NetworkFilmsDetailsRepository

class NetworkDetailsRepositoryImpl(
   private val filmsApi: DetailFilmsApi
):NetworkFilmsDetailsRepository {

    override suspend fun getFilmForID(id:Int) = filmsApi.getFilmsByID(id)

    override suspend fun getAllStaffFilmsByID(filmsID:Int) = filmsApi.getStaffFilmsByID(filmsID)

    override suspend fun getGalleryFilmsByID(filmsID:Int) = filmsApi.getGalleryFilmsByID(filmsID)

    override suspend fun getSimilarFilmsByID(filmsID:Int) = filmsApi.getSimilarFilmsByID(filmsID).items

}