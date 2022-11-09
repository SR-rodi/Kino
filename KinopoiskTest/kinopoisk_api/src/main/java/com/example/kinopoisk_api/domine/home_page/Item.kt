package com.example.kinopoisk_api.domine.home_page

import com.example.kinopoisk_api.domine.all.Country
import com.example.kinopoisk_api.domine.all.Genre
import com.example.kinopoisk_api.domine.home_page.HomePageItem
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("kinopoiskId") override val filmId: Int,
    override val nameRu: String,
    override val genres: List<Genre>,
    override val posterUrlPreview: String,
    private val ratingKinopoisk: Double,
    val countries: List<Country>,
    val imdbId: String,
    val nameEn: Any,
    val nameOriginal: String,
    val posterUrl: String,
    val ratingImdb: Double,
    val type: String,
    val year: Int
) : HomePageItem {
    override val rating: String? = ratingKinopoisk.toString()

}