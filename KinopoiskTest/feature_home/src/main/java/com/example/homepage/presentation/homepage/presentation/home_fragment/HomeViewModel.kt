package com.example.homepage.presentation.homepage.presentation.home_fragment

import androidx.lifecycle.viewModelScope
import com.example.core.tools.*
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.all.LoadState
import com.example.feature_database.repository.CollectionsFilmsRepository
import com.example.feature_database.repository.DataBaseRepository
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
    private val counterGenreRepository: DataBaseRepository,
    private val collectionsRepository: CollectionsFilmsRepository
) : BaseViewModel() {

    private val calendar = Calendar.getInstance()

    private var _homePageList =
        MutableStateFlow<List<HomePadeList>>(emptyList())
    val homePageList = _homePageList.asStateFlow()

    init {
        getHomePageList()
    }

    private fun getHomePageList() {
        viewModelScope.launch(handler + Dispatchers.IO) {
            _loadState.value = LoadState.LOADING
            loadAllFilms(
                calendar.get(Calendar.YEAR),
                (calendar.get(Calendar.MONTH) + 1),
                getCounterAndGenreList()
            ).mergeHomeDatabase(counterGenreRepository, _homePageList, viewModelScope)
            _loadState.value = LoadState.SUCCESS
        }
    }

    fun createStartCollection() {
        viewModelScope.launch(Dispatchers.IO) {
            collectionsRepository.addCollection("Любимое", ID_LIKE_COLLECTION)
            collectionsRepository.addCollection("Хочу посмотреть", ID_FAVORITE_COLLECTION)
            collectionsRepository.addCollection("Вам было интересно", ID_HISTORY_COLLECTION)
            collectionsRepository.addCollection("Просмотренно", ID_LOOK_COLLECTION)
        }
    }

    private suspend fun getCounterAndGenreList(): CountryAndGenreDTO {
        var counterAndGenre = CountryAndGenreDTO(
            counterGenreRepository.getAllCounter(),
            counterGenreRepository.getAllGenre()
        )

        if (counterAndGenre.countries.isEmpty() || counterAndGenre.genres.isEmpty())
            counterAndGenre = loadNetworkGenresAndCountries()

        return counterAndGenre

    }

    private suspend fun loadNetworkGenresAndCountries(): CountryAndGenreDTO {
        val counterAndGenre = networkRepository.getListGenreAndCounter()
        counterGenreRepository.insertCounter(counterAndGenre.countries)
        counterGenreRepository.insertGenre(counterAndGenre.genres)
        return counterAndGenre
    }

    private suspend fun loadAllFilms(
        year: Int,
        month: Int,
        counterAndGenre: CountryAndGenreDTO
    ): List<HomePadeList> {

        val counter = counterAndGenre.getRandomCounter()
        val genre = counterAndGenre.getRandomGenre()

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
                loadPopular(networkRepository, FIRST_PAGE).createListForView(
                    SIZE_LIST_VIEW
                )
            )
        )
    }

    companion object {
        const val FIRST_PAGE = 1
        const val SIZE_LIST_VIEW = 18
    }

}