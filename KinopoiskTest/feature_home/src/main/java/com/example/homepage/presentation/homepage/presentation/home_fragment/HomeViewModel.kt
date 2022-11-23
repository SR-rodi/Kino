package com.example.homepage.presentation.homepage.presentation.home_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.tools.BaseViewModel
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.all.LoadState
import com.example.feature_database.DataBaseRepository
import com.example.homepage.presentation.homepage.data.films.dto.CountryAndGenreDTO
import com.example.homepage.presentation.homepage.data.list_info.HomePadeList
import com.example.homepage.presentation.homepage.domaine.NetworkCategoryImpl
import com.example.homepage.presentation.homepage.tools.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
    private val networkRepository: NetworkCategoryImpl,
    private val dataBaseRepository: DataBaseRepository
) : BaseViewModel() {

    private val calendar = Calendar.getInstance()

    private val _homePageList =
        MutableStateFlow<List<HomePadeList>>(emptyList())
    val homePageList = _homePageList.asStateFlow()

    init {
        getHomePageList()
    }

    private fun getHomePageList() {
        viewModelScope.launch(handler + Dispatchers.IO) {
            _loadState.value = LoadState.LOADING
            _homePageList.value =
                loadAllFilms(
                    calendar.get(Calendar.YEAR),
                    (calendar.get(Calendar.MONTH) + 1),
                    getCounterAndGenreList()
                )
            _loadState.value = LoadState.SUCCESS
        }
    }

    private suspend fun getCounterAndGenreList(): CountryAndGenreDTO {
        var counterAndGenre = CountryAndGenreDTO(
            dataBaseRepository.getAllCounter(),
            dataBaseRepository.getAllGenre()
        )

        if (counterAndGenre.countries.isEmpty() || counterAndGenre.genres.isEmpty())
            counterAndGenre = loadNetworkGenresAndCountries()

        return counterAndGenre

    }

    private suspend fun loadNetworkGenresAndCountries(): CountryAndGenreDTO {
        val counterAndGenre = networkRepository.getListGenreAndCounter()
        counterAndGenre.countries.forEach { country ->
            dataBaseRepository.insertCounter(country)
        }
        counterAndGenre.genres.forEach { genre ->
            dataBaseRepository.insertGenre(genre)
        }
        return counterAndGenre
    }

    private suspend fun loadAllFilms(
        year: Int,
        month: Int,
        counterAndGenre: CountryAndGenreDTO
    ): List<HomePadeList> {

        val counter = counterAndGenre.getRandomCounter()
        val genre = counterAndGenre.getRandomGenre(dataBaseRepository)

        return listOf(
            HomePadeList(
                CategoryFilms.PREMIERS.createPrimersCategory(year, month),
                loadPremieres(networkRepository, year, month.converterInMonth()).createListForView(
                    SIZE_LIST_VIEW
                )
            ),
            HomePadeList(
                CategoryFilms.RANDOM.createRandomCategory(counter, genre),
                loadFilmsByCounterAdnGenre(networkRepository, FIRST_PAGE, counter.id, genre.id)
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