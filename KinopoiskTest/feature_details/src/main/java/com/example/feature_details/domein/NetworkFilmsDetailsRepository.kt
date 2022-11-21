package com.example.feature_details.domein

import com.example.feature_details.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.feature_details.data.detailsFilm_page.response.ResponseFilmGallery
import com.example.feature_details.data.detailsFilm_page.dto.StaffFromFilmsDTO
import com.example.feature_details.data.detailsFilm_page.dto.SimilarFilmsDTO
import com.example.feature_details.data.details_staff.StaffDetailsDTO

interface NetworkFilmsDetailsRepository {

    suspend fun getFilmForID(id:Int): InfoFilmDTO

    suspend fun getAllStaffFilmsByID(filmsID:Int): List<StaffFromFilmsDTO>

    suspend fun getGalleryFilmsByID(filmsID:Int,type:String="",page:Int=1): ResponseFilmGallery

    suspend fun getSimilarFilmsByID(filmsID:Int,): List<SimilarFilmsDTO>


}