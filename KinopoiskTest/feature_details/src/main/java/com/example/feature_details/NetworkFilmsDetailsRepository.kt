package com.example.feature_details

import com.example.feature_details.detailsFilm_page.dto.InfoFilmDTO
import com.example.feature_details.detailsFilm_page.response.ResponseFilmGallery
import com.example.feature_details.detailsFilm_page.dto.StaffFromFilmsDTO
import com.example.feature_details.detailsFilm_page.dto.SimilarFilmsDTO

interface NetworkFilmsDetailsRepository {

    suspend fun getFilmForID(id:Int): InfoFilmDTO

    suspend fun getAllStaffFilmsByID(filmsID:Int): List<StaffFromFilmsDTO>

    suspend fun getGalleryFilmsByID(filmsID:Int): ResponseFilmGallery

    suspend fun getSimilarFilmsByID(filmsID:Int): List<SimilarFilmsDTO>
}