package com.example.homepage.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk_api.CategoryFilms
import com.example.core.tools.*
import com.example.homepage.data.HomePadeList
import com.example.kinopoisk_api.*
import com.example.kinopoisk_api.domine.repo.NetworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
    private val networkRepository: NetworkRepository
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




       val a=   loadFilmsByCounterAdnGenre(networkRepository, FIRST_PAGE,counter.id,genre.id)
            .createListForView(SIZE_LIST_VIEW)
        Log.d("Kart","$a , ${counter.id} , ${genre.id}")

        return listOf(
            HomePadeList(
                CategoryFilms.PREMIERS,
                loadPremieres(networkRepository, year, month.converterInMonth()).createListForView(SIZE_LIST_VIEW)
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