package com.example.screen_listpage.data

import com.example.core.tools.all.BaseFilms
import com.example.core.tools.general.Country
import com.example.core.tools.general.Genre
import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("kinopoiskId")  override val filmId: Int,
    val ratingKinopoisk: Double,
    override val rating: String? =ratingKinopoisk.toString(),
    override val nameRu: String,
    override val genres: List<Genre>,
    override val posterUrlPreview: String,
    val nameEn: String?,
    val nameOriginal: String,
    val posterUrl: String,
    val type: String,
    val year: Int,

):BaseFilms