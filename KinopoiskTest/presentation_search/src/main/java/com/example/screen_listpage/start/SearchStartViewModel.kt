package com.example.screen_listpage.start

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.core.tools.all.CategoryFilms
import com.example.screen_listpage.data.Film
import com.example.screen_listpage.data.SetSearch
import com.example.screen_listpage.network.SearchPagingRepo
import com.example.screen_listpage.network.SearchRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class SearchStartViewModel(
    private val networkRepository: SearchRepositoryImpl,
    private val searchPagingRepo: SearchPagingRepo
) : ViewModel() {

    var searchResponse = MutableSharedFlow<PagingData<Film>>()



    @OptIn(FlowPreview::class)
    fun search(keyWordFlow: Flow<String>) = keyWordFlow.debounce(500)
        .distinctUntilChanged()
        .onEach { keyWord ->
        }
        .launchIn(viewModelScope + Dispatchers.IO)
}
