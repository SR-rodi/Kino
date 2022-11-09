package com.example.kinopoisk_api.domine.home_page.films

import com.example.kinopoisk_api.domine.all.Country
import com.example.kinopoisk_api.domine.all.Genre
import com.example.kinopoisk_api.domine.home_page.HomePageItem

data class FilmsDTO(
    override val posterUrlPreview: String,
    override val rating: String? = null,
    override val nameRu: String,
    override val genres: List<Genre>,
    override val filmId: Int,
    val countries: List<Country>,
    val filmLength: String,
    val nameEn: String,
    val posterUrl: String,
    val ratingChange: Any,
    val ratingVoteCount: Int,
    val year: String
): HomePageItem