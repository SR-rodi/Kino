package com.example.feature_details.domein.repository_ipl

import com.example.feature_details.domein.DetailFilmsApi
import com.example.feature_details.domein.NetworkFilmsDetailsRepository

class NetworkFilmDetailsRepositoryImpl(
   private val filmsApi: DetailFilmsApi
): NetworkFilmsDetailsRepository {

    override suspend fun getFilmForID(id:Int) = filmsApi.getFilmsByID(id)

    override suspend fun getAllStaffFilmsByID(filmsID:Int) = filmsApi.getStaffFilmsByID(filmsID)

    override suspend fun getGalleryFilmsByID(filmsID:Int,type:String,page:Int) = filmsApi.getGalleryFilmsByID(filmsID,type,page)

    override suspend fun getSimilarFilmsByID(filmsID:Int) = filmsApi.getSimilarFilmsByID(filmsID).items

}