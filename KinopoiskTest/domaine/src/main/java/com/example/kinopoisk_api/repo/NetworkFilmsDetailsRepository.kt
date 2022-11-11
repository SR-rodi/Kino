package com.example.kinopoisk_api.repo

import com.example.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.data.detailsFilm_page.response.ResponseFilmGallery
import com.example.data.detailsFilm_page.dto.StaffFromFilmsDTO
import com.example.data.detailsFilm_page.dto.SimilarFilmsDTO

interface NetworkFilmsDetailsRepository {

    suspend fun getFilmForID(id:Int): InfoFilmDTO

    suspend fun getAllStaffFilmsByID(filmsID:Int): List<StaffFromFilmsDTO>

    suspend fun getGalleryFilmsByID(filmsID:Int): ResponseFilmGallery

    suspend fun getSimilarFilmsByID(filmsID:Int): List<SimilarFilmsDTO>
}