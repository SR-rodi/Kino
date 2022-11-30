package com.example.feature_details.data.detailsFilm_page.model

import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.general.InfoFilms

class CategorySimilar (
    val listValue: List<NestedInfoInCategory>,
    val category: String = "Похожие фильмы",
): InfoFilms