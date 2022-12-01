package com.example.homepage.presentation.homepage.tools

import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.all.Query
import com.example.core.tools.base_model.category.BaseCategory
import com.example.core.tools.base_model.films.BaseFilm
import com.example.homepage.presentation.homepage.data.list_info.HomePageCategory
import com.example.core.tools.category.CategoryInfo
import com.example.core.tools.extensions.createListForView
import com.example.homepage.presentation.homepage.domaine.NetworkCategoryImpl
import com.example.homepage.presentation.homepage.presentation.home_fragment.HomeViewModel
import com.example.homepage.presentation.homepage.presentation.home_fragment.HomeViewModel.Companion.FIRST_PAGE
import com.example.homepage.presentation.homepage.presentation.home_fragment.HomeViewModel.Companion.SIZE_LIST_VIEW


fun CategoryInfo.createCategory(list: List<NestedInfoInCategory>, query: Query? = null): HomePageCategory =
    if (query == null) HomePageCategory(this, list)
    else HomePageCategory(this, list, query)
