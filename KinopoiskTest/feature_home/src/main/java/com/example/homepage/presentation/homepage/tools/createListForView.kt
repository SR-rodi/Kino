package com.example.homepage.presentation.homepage.tools

import com.example.adapterdelegate.data.SimilarFilmsDTO
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.all.Query
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.category.CategoryInfo
import com.example.core.tools.base_model.category.PageCategory


fun CategoryInfo.createCategory(list: List<NestedInfoInCategory>, query: Query? = null): PageCategory =
    if (query == null) PageCategory(this, list)
    else PageCategory(this, list, query)

fun List<SimilarFilmsDTO>.toListBaseFilms(): MutableList<BaseFilm> {
    val list = mutableListOf<BaseFilm>()
    this.forEach {
        list.add(BaseFilm(it.nameRu,it.posterUrlPreview, filmId = it.filmId))
    }
    return list
}