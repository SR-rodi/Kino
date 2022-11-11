package com.example.data.detailsFilm_page.dto

import com.example.data.detailsFilm_page.model.NestedInoFilms

data class SimilarFilmsDTO(
    val filmId: Int,
    val nameRu: String,
    val posterUrlPreview: String,
): NestedInoFilms