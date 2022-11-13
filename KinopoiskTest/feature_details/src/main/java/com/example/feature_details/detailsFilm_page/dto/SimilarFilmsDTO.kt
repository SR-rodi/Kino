package com.example.feature_details.detailsFilm_page.dto

import com.example.feature_details.detailsFilm_page.model.NestedInoFilms

data class SimilarFilmsDTO(
    val filmId: Int,
    val nameRu: String,
    val posterUrlPreview: String,
): NestedInoFilms