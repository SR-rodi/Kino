package com.example.data.home_page.films.dto

import com.example.data.home_page.films.general.Country
import com.example.data.home_page.films.general.Genre

class CountryAndGenreDTO(
    val countries: List<Country>,
    val genres: List<Genre>
)