package com.example.screen_listpage.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.extensions.toBaseFilmList
import com.example.screen_listpage.data.SearchFilm
import com.example.screen_listpage.data.SetSearch
import com.example.screen_listpage.network.SearchPagingRepo
import com.example.screen_listpage.network.SearchRepositoryImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class SearchStartViewModel(
    private val networkRepository: SearchRepositoryImpl,
    private val searchPagingRepo: SearchPagingRepo
) : ViewModel() {

    private val _films = MutableSharedFlow<List<BaseFilm>>()
    val films = _films.asSharedFlow()

    var keyWordStartSearch = ""

    fun search(keyWord: String, after: Int) {
        viewModelScope.launch {
            keyWordStartSearch = keyWord
            delay(500)
            if (keyWordStartSearch == "") _films.emit(emptyList())
            if (keyWordStartSearch.length == after && keyWordStartSearch.isNotEmpty())
                _films.emit((networkRepository.getSearchFilms(SetSearch, keyWordStartSearch, 1).items).toBaseFilmList())
        }
    }


}
