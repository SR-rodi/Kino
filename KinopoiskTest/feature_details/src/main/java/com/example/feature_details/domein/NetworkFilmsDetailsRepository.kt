package com.example.feature_details.domein

import com.example.feature_details.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.feature_details.data.detailsFilm_page.response.ResponseFilmGallery
import com.example.adapterdelegate.data.StaffFromFilmsDTO
import com.example.adapterdelegate.data.SimilarFilmsDTO

interface NetworkFilmsDetailsRepository {

    suspend fun getFilmForID(id:Int): InfoFilmDTO

    suspend fun getAllStaffFilmsByID(filmsID:Int): List<StaffFromFilmsDTO>

    suspend fun getGalleryFilmsByID(filmsID:Int,type:String="",page:Int=1): ResponseFilmGallery

    suspend fun getSimilarFilmsByID(filmsID:Int,): List<SimilarFilmsDTO>


}