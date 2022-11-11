package com.example.data.home_page.list_info

import com.example.data.home_page.all.CategoryFilms
import com.example.data.home_page.films.BaseFilms

data class HomePadeList(
    val category: CategoryFilms,
    val filmList: List<BaseFilms>
)