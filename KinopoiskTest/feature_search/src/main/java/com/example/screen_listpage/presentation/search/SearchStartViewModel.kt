package com.example.screen_listpage.presentation.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.tools.NAVIGATE__TO_INFO_FILM
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.extensions.toBaseFilmList
import com.example.core.tools.SetSearch
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.category.PageCategory
import com.example.screen_listpage.network.SearchPagingRepo
import com.example.screen_listpage.network.SearchRepositoryImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class SearchStartViewModel(
    private val networkRepository: SearchRepositoryImpl,
    private val savedStateHandle: SavedStateHandle,
    private val searchPagingRepo: SearchPagingRepo
) : ViewModel() {

    private val _films = MutableSharedFlow<List<NestedInfoInCategory>>()
    val films = _films.asSharedFlow()

    var keyWordStartSearch = ""

    fun search(keyWord: String, after: Int) {
        viewModelScope.launch {
            keyWordStartSearch = keyWord
            delay(500)
            if (keyWordStartSearch == "") _films.emit(emptyList())
            if (keyWordStartSearch.length == after && keyWordStartSearch.isNotEmpty())
                _films.emit((networkRepository.getSearchFilms(SetSearch, keyWordStartSearch, 1).items))
        }
    }

    fun navigate(pageCategory: PageCategory) {
        savedStateHandle[NAVIGATE__TO_INFO_FILM] = pageCategory.query?.id
    }
}
