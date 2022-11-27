package com.example.homepage.presentation.homepage.data.list_info

import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.all.BaseFilms

class HomePadeList(
    val category: CategoryFilms,
    val filmList: List<BaseEntityFilm>
)