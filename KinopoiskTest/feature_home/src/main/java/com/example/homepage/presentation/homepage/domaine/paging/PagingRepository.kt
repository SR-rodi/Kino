package com.example.homepage.presentation.homepage.domaine.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.category.CategoryInfo
import com.example.feature_database.repository.DataBaseRepository
import com.example.homepage.presentation.homepage.domaine.NetworkCategoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class PagingRepository(
    private val networkRepository: NetworkCategoryImpl,
    private val dataBaseRepository: DataBaseRepository
) {
    fun getFlowFilms(category: CategoryInfo?, viewModelScope: CoroutineScope): Flow<PagingData<BaseFilm>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ListPagePagingSource(networkRepository,
                dataBaseRepository,viewModelScope, category) }
        ).flow
    }
}