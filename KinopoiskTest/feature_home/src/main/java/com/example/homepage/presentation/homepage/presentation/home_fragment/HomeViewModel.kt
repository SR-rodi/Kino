package com.example.homepage.presentation.homepage.presentation.home_fragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.tools.*
import com.example.core.tools.category.CategoryInfo
import com.example.core.tools.all.LoadState
import com.example.core.tools.all.Query
import com.example.core.tools.base_model.category.BaseCategory
import com.example.core.tools.base_model.category.StartCategory
import com.example.core.tools.extensions.createListForView
import com.example.feature_database.repository.CollectionsFilmsRepository
import com.example.feature_database.repository.DataBaseRepository
import com.example.homepage.presentation.homepage.data.films.dto.CountryAndGenreDTO
import com.example.homepage.presentation.homepage.data.list_info.HomePadeList
import com.example.homepage.presentation.homepage.data.list_info.HomePageCategory
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
    private val collectionsRepository: CollectionsFilmsRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val calendar = Calendar.getInstance()

    private var _homePageList =
        MutableStateFlow<List<BaseCategory>>(emptyList())
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
    ): List<HomePageCategory> {
        val query = Query().apply {
            counterID =counterAndGenre.getRandomCounter().id
            genreId =counterAndGenre.getRandomGenre().id
            this.year = year
            this.month = month.converterInMonth()
        }

              return  listOf(
                  CategoryInfo.PREMIERS.createCategory(
                      loadPremieres(networkRepository,query.year,query.month)
                          .createListForView(SIZE_LIST_VIEW),query),
                  CategoryInfo.BEST.createCategory(
                      loadBest(networkRepository, FIRST_PAGE).createListForView(SIZE_LIST_VIEW)),
                  CategoryInfo.POPULAR.createCategory(
                      loadPopular(networkRepository, FIRST_PAGE).createListForView(SIZE_LIST_VIEW)),
                  CategoryInfo.RANDOM.createCategory(
                      loadFilmsByCounterAdnGenre(networkRepository, FIRST_PAGE, query.counterID, query.genreId)
                      .createListForView(SIZE_LIST_VIEW),query)
              )
    }

    fun navigateToCategory(categoryFilms: BaseCategory) {
        savedStateHandle[NAVIGATE__CATEGORY_PAGE] = categoryFilms as HomePageCategory
        savedStateHandle[WORK_MODE] = ViewModelWorkMode.IN_NETWORK
    }

    fun navigateToInfoFilm(filmID: Int) {
        savedStateHandle[NAVIGATE__TO_INFO_FILM] = filmID

    }

    companion object {
        const val FIRST_PAGE = 1
        const val SIZE_LIST_VIEW = 18
    }

}