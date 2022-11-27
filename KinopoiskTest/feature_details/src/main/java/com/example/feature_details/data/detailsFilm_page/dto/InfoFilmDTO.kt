package com.example.feature_details.data.detailsFilm_page.dto

import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.general.Country
import com.example.core.tools.general.Genre


class InfoFilmDTO(
    val kinopoiskId: Int,
    val countries: List<Country>,
    val description: String?,
    val filmLength: Int,
    val genres: List<Genre>,
    val logoUrl: Any,
    val nameEn: String?,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val ratingKinopoisk: Double,
    val ratingAgeLimits:String?,
    val serial: Boolean,
    val shortDescription: String?,
    val year: Int
){
    var isFavorite = false
    var isLike = false
    var isLook = false

}