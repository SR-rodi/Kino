package com.example.homepage.presentation.homepage.domaine.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.tools.all.CategoryFilms
import com.example.homepage.presentation.homepage.data.films.BaseFilms
import com.example.homepage.presentation.homepage.domaine.NetworkCategoryImpl
import kotlinx.coroutines.flow.Flow

class PagingRepository(
    private val networkRepository: NetworkCategoryImpl
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