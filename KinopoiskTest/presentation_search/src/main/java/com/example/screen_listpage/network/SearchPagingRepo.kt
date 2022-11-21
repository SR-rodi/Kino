package com.example.screen_listpage.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.screen_listpage.data.Film
import kotlinx.coroutines.flow.Flow

class SearchPagingRepo( private val networkRepository: SearchRepositoryImpl) {

    fun getFilms(keyWord:String): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(networkRepository,keyWord) }
        ).flow
    }
}