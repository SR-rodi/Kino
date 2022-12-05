package com.example.homepage.presentation.homepage.tools

import com.example.core.tools.base_model.category.BaseCategory
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.extensions.toBaseFilmList
import com.example.feature_database.repository.DataBaseRepository
import com.example.core.tools.base_model.category.PageCategory
import com.example.homepage.presentation.homepage.domain.NetworkCategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

suspend fun loadPremieres(
    networkRepository: NetworkCategoryRepository,
    year: Int, month: String
) = networkRepository.getPremieresInNetwork(year, month).toBaseFilmList()

suspend fun loadBest(
    networkRepository: NetworkCategoryRepository,
    page: Int
) = networkRepository.getBestFilmsInNetwork(page).toBaseFilmList()

suspend fun loadPopular(
    networkRepository: NetworkCategoryRepository,
    page: Int
) = networkRepository.getPopularInNetwork(page).toBaseFilmList()


suspend fun loadFilmsByCounterAdnGenre(
    networkRepository: NetworkCategoryRepository,
    page: Int,
    counterId: Int,
    genreId: Int
) = networkRepository.getFilmsGenreAndCounter(page, counterId, genreId).toBaseFilmList()

