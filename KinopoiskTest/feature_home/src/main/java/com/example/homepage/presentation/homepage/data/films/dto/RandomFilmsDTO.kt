package com.example.homepage.presentation.homepage.data.films.dto

import com.example.core.tools.general.Genre
import com.example.core.tools.all.BaseFilms
import com.google.gson.annotations.SerializedName

data class RandomFilmsDTO(
    @SerializedName("kinopoiskId") override val filmId: Int,
    override val nameRu: String,
    override val genres: List<Genre>,
    override val posterUrlPreview: String,
    private val ratingKinopoisk: Double,
) : BaseFilms {
    override val rating: String = ratingKinopoisk.toString()

}