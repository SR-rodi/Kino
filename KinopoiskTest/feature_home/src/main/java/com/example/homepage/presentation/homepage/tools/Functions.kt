package com.example.homepage.presentation.homepage.tools

import com.example.core.tools.all.BaseEntityFilm
import com.example.feature_database.repository.DataBaseRepository
import com.example.homepage.presentation.homepage.data.list_info.HomePadeList
import com.example.homepage.presentation.homepage.domaine.NetworkCategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

suspend fun loadPremieres(
    networkRepository: NetworkCategoryRepository,
    year: Int, month: String
) = networkRepository.getPremieresInNetwork(year, month)

suspend fun loadBest(
    networkRepository: NetworkCategoryRepository,
    page: Int
) =
    networkRepository.getBestFilmsInNetwork(page)

suspend fun loadPopular(
    networkRepository: NetworkCategoryRepository,
    page: Int
) = networkRepository.getPopularInNetwork(page)


suspend fun loadFilmsByCounterAdnGenre(
    networkRepository: NetworkCategoryRepository,
    page: Int,
    counterId: Int,
    genreId: Int
) = networkRepository.getFilmsGenreAndCounter(page, counterId, genreId)


fun List<BaseEntityFilm>.mergeDatabase(dataBaseRepository: DataBaseRepository,viewModelScope: CoroutineScope): List<BaseEntityFilm> {
    val listFilmsId = mutableListOf<Int>()
    this.forEach { film -> listFilmsId.add(film.filmId) }
    dataBaseRepository.getStatusFilmList(listFilmsId) .onEach { map->
            this.forEach { film ->
        film.isLook = map[film.filmId] ?: false
    }
    }.launchIn(viewModelScope)

    return this
}

suspend fun List<HomePadeList>.mergeHomeDatabase(
    dataBaseRepository: DataBaseRepository,
    _homePageList: MutableStateFlow<List<HomePadeList>>,
    viewModelScope: CoroutineScope
) {

    val listFilmsId = mutableListOf<Int>()
    this.forEach { list ->
        list.filmList.forEach { film ->
            listFilmsId.add(film.filmId)
        }
    }
    dataBaseRepository.getStatusFilmList(listFilmsId).onEach { mapData ->
        this.forEach { list ->
            list.filmList.forEach { film ->
                film.isLook = mapData[film.filmId] ?: false
            }
        }
        _homePageList.value = this
    }.launchIn(viewModelScope)
}