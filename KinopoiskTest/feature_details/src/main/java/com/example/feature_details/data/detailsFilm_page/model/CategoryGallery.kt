package com.example.feature_details.data.detailsFilm_page.model

import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.general.InfoFilms

class CategoryGallery(
    val listValue: List<NestedInfoInCategory>,
    val category: String = "Галерея",
): InfoFilms
