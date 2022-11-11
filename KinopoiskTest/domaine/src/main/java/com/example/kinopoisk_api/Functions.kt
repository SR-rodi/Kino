package com.example.kinopoisk_api

import com.example.kinopoisk_api.repo.NetworkCategoryRepository

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