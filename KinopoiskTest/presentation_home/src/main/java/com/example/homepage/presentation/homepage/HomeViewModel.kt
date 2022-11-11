package com.example.homepage.presentation.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.tools.extensions.*
import com.example.kinopoisk_api.loadBest
import com.example.kinopoisk_api.loadFilmsByCounterAdnGenre
import com.example.kinopoisk_api.loadPopular
import com.example.kinopoisk_api.loadPremieres
import com.example.data.home_page.all.CategoryFilms
import com.example.data.home_page.list_info.HomePadeList
import com.example.kinopoisk_api.repo.NetworkCategoryRepository
import com.example.kinopoisk_api.repository_ipl.NetworkCategoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
    private val networkRepository: NetworkCategoryImpl
) : ViewModel() {

    private val calendar = Calendar.getInstance()

    private val _homePageList =
        MutableStateFlow<List<HomePadeList>>(emptyList())
    val homePageList = _homePageList.asStateFlow()

    init {
        getHomePageList()
    }

    private fun getHomePageList() {
        viewModelScope.launch {
            _homePageList.value =
                loadAllFilms(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1))
        }
    }

    private suspend fun loadAllFilms(
        year: Int,
        month: Int
    ): List<HomePadeList> {

        val counterAndGenre = networkRepository.getListGenreAndCounter() // пока оствляем запрос так, но когда подключим базу данныйх перенесем туда
        val counter = counterAndGenre.getRandomCounter()
        val genre = counterAndGenre.getRandomGenre()

        return listOf(
            HomePadeList(
                CategoryFilms.PREMIERS.createPrimersCategory(year,month),
                loadPremieres(networkRepository, year, month.converterInMonth()).createListForView(
                    SIZE_LIST_VIEW
                )
            ),
            HomePadeList(
                CategoryFilms.RANDOM.createRandomCategory(counter,genre),
                loadFilmsByCounterAdnGenre(networkRepository, FIRST_PAGE,counter.id,genre.id)
                    .createListForView(SIZE_LIST_VIEW)
            ),
            HomePadeList(
                CategoryFilms.BEST,
                loadBest(networkRepository, FIRST_PAGE).createListForView(SIZE_LIST_VIEW)
            ),
            HomePadeList(
                CategoryFilms.POPULAR,
                loadPopular(networkRepository, FIRST_PAGE).createListForView(SIZE_LIST_VIEW)
            ),
        )
    }


    companion object {
        const val FIRST_PAGE = 1
        const val SIZE_LIST_VIEW = 5
    }

}