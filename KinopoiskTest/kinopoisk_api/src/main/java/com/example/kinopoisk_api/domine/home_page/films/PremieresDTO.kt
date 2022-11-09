package com.example.kinopoisk_api.domine.home_page.films

import com.example.kinopoisk_api.domine.all.Country
import com.example.kinopoisk_api.domine.all.Genre
import com.example.kinopoisk_api.domine.home_page.HomePageItem
import com.google.gson.annotations.SerializedName

data class PremieresDTO(
    @SerializedName("kinopoiskId") override val filmId: Int,
    override val nameRu: String,
    override val genres: List<Genre>,
    override val posterUrlPreview: String,
    val countries: List<Country>,
    val duration: Int,
    val nameEn: String,
    val posterUrl: String,
    val premiereRu: String,
    val year: Int
) : HomePageItem {
    override val rating: String? = null
}

