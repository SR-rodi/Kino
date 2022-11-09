package com.example.homepage.data

import com.example.kinopoisk_api.CategoryFilms
import com.example.kinopoisk_api.domine.home_page.HomePageItem

data class HomePadeList(
    val category: CategoryFilms,
    val filmList: List<HomePageItem>
)