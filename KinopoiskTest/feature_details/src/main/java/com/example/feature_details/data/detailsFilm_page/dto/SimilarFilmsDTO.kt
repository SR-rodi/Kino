package com.example.feature_details.data.detailsFilm_page.dto

import com.example.core.tools.all.NestedInoFilms

data class SimilarFilmsDTO(
    val filmId: Int,
    val nameRu: String,
    val posterUrlPreview: String,
): NestedInoFilms