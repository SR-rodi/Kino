package com.example.kinopoisk_api.domine.home_page

import com.example.kinopoisk_api.domine.all.Country
import com.example.kinopoisk_api.domine.all.Genre

data class CountryAndGenreDTO(
    val countries: List<Country>,
    val genres: List<Genre>
)