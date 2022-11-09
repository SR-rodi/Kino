package com.example.screen_listpage

import androidx.lifecycle.ViewModel
import com.example.kinopoisk_api.CategoryFilms
import com.example.kinopoisk_api.domine.repo.NetworkRepository
import com.example.kinopoisk_api.domine.repo.PagingRepository

class CategoryPageViewModel(
    private val networkRepository: PagingRepository
) : ViewModel() {

    fun getFilms(categoryFilms: CategoryFilms) {
        networkRepository.getFlowFilms(categoryFilms)
    }
}