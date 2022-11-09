package com.example.kinopoisk_api

import com.example.kinopoisk_api.domine.repo.NetworkRepository


suspend fun loadPremieres(networkRepository: NetworkRepository, year: Int, month: String) =
    networkRepository.getPremieresInNetwork(year, month)

suspend fun loadBest(networkRepository: NetworkRepository, page: Int) =
    networkRepository.getBestFilmsInNetwork(page)

suspend fun loadPopular(networkRepository: NetworkRepository, page: Int) =
    networkRepository.getPopularInNetwork(page)

suspend fun loadFilmsByCounterAdnGenre(
    networkRepository: NetworkRepository,
    page: Int,
    counterId: Int,
    genreId: Int
) = networkRepository.getFilmsGenreAndCounter(page, counterId, genreId)