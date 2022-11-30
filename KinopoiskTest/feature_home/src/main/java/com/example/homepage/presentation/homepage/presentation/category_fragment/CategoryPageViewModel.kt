package com.example.homepage.presentation.homepage.presentation.category_fragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.core.tools.*
import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.all.CategoryFilms
import com.example.homepage.presentation.homepage.domaine.paging.PagingRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CategoryPageViewModel(
    private val networkRepository: PagingRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val categoryFilms: CategoryFilms? = savedStateHandle[NAVIGATE__CATEGORY_PAGE]
    val mode:ViewModelWorkMode? = savedStateHandle[WORK_MODE]

    fun getFilms(): StateFlow<PagingData<BaseEntityFilm>> =
        networkRepository.getFlowFilms(categoryFilms, viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun getNameCategory(): String? = categoryFilms?.name

    fun navigateToFilmsInfo(filmId: Int) {
            savedStateHandle[NAVIGATE__TO_INFO_FILM] = filmId
    }
}
