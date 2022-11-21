package com.example.feature_details.data.detailsFilm_page.model

import com.example.core.tools.all.NestedInoFilms

class CategorySimilar (
    val listValue: List<NestedInoFilms>,
    val category: String = "Похожие фильмы",
): InfoFilms