package com.example.homepage.presentation.homepage.presentation.category_fragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.core.tools.*
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.category.BaseCategory
import com.example.core.tools.base_model.category.StartCategory
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.category.CategoryInfo
import com.example.homepage.presentation.homepage.domaine.paging.PagingRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CategoryPageViewModel(
    private val networkRepository: PagingRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val categoryFilms: StartCategory? = savedStateHandle[NAVIGATE__CATEGORY_PAGE]

    fun msmd(){
     val a=   networkRepository.getFlowFilms(categoryFilms, viewModelScope)
    }
    fun getFilms(): StateFlow<PagingData<NestedInfoInCategory>> =
        networkRepository.getFlowFilms(categoryFilms, viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun getNameCategory(): String? = categoryFilms?.category?.name

    fun navigateToFilmsInfo(filmId: Int) {
        savedStateHandle[NAVIGATE__TO_INFO_FILM] = filmId
    }
}
