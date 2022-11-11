package com.example.kinopoisk_api.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.home_page.all.CategoryFilms
import com.example.data.home_page.films.BaseFilms
import com.example.kinopoisk_api.repo.NetworkCategoryRepository
import kotlinx.coroutines.flow.Flow

class PagingRepository(
    private val networkRepository: NetworkCategoryRepository
) {
    fun getFlowFilms(category: CategoryFilms): Flow<PagingData<BaseFilms>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ListPagePagingSource(networkRepository,category) }
        ).flow
    }
}