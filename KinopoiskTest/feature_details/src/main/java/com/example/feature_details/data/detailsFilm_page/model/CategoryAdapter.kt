package com.example.feature_details.data.detailsFilm_page.model

import com.example.core.tools.all.NestedInoFilms

class CategoryAdapter(
    val category: String,
    val listValue: List<NestedInoFilms>,
    var isActor :Boolean = false
) : InfoFilms


