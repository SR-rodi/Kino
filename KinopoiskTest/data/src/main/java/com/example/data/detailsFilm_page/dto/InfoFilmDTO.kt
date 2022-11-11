package com.example.data.detailsFilm_page.dto

import com.example.data.home_page.films.general.Country
import com.example.data.home_page.films.general.Genre

class InfoFilmDTO(
    val kinopoiskId: Int,
    val countries: List<Country>,
    val description: String,
    val filmLength: Int,
    val genres: List<Genre>,
    val logoUrl: Any,
    val nameEn: Any,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val ratingKinopoisk: Double,
    val ratingAgeLimits:String,
    val serial: Boolean,
    val shortDescription: String,
    val type: String,
    val webUrl: String,
    val year: Int
)

