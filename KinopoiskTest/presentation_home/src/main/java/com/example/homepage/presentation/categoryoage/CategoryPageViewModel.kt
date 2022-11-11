package com.example.homepage.presentation.categoryoage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.data.home_page.all.CategoryFilms
import com.example.kinopoisk_api.paging.PagingRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CategoryPageViewModel(
    private val networkRepository: PagingRepository
) : ViewModel() {

    fun getFilms(categoryFilms: CategoryFilms) =
        networkRepository.getFlowFilms(categoryFilms)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

}
