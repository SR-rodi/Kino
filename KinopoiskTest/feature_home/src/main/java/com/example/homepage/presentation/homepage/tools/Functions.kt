package com.example.homepage.presentation.homepage.tools

import com.example.homepage.presentation.homepage.domaine.NetworkCategoryRepository

suspend fun loadPremieres(networkRepository: NetworkCategoryRepository, year: Int, month: String)=
    networkRepository.getPremieresInNetwork(year, month)

suspend fun loadBest(networkRepository: NetworkCategoryRepository, page: Int) =
    networkRepository.getBestFilmsInNetwork(page)

suspend fun loadPopular(networkRepository: NetworkCategoryRepository, page: Int) =
    networkRepository.getPopularInNetwork(page)

suspend fun loadFilmsByCounterAdnGenre(
    networkRepository: NetworkCategoryRepository,
    page: Int,
    counterId: Int,
    genreId: Int
) = networkRepository.getFilmsGenreAndCounter(page, counterId, genreId)