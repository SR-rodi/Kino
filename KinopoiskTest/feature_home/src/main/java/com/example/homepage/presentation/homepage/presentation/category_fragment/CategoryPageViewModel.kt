package com.example.homepage.presentation.homepage.presentation.category_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.all.CategoryFilms
import com.example.homepage.presentation.homepage.domaine.paging.PagingRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CategoryPageViewModel(
    private val networkRepository: PagingRepository
) : ViewModel() {


    fun getFilms(categoryFilms: CategoryFilms): StateFlow<PagingData<BaseEntityFilm>> {
       return networkRepository.getFlowFilms(categoryFilms,viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }


}
