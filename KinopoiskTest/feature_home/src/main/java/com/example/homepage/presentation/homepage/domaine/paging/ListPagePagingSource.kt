package com.example.homepage.presentation.homepage.domaine.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.category.CategoryInfo
import com.example.feature_database.repository.DataBaseRepository
import com.example.homepage.presentation.homepage.domaine.NetworkCategoryRepository
import com.example.homepage.presentation.homepage.tools.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListPagePagingSource(
    private val networkRepository: NetworkCategoryRepository,
    private val dataBaseRepository: DataBaseRepository,
    private val viewModelScope: CoroutineScope,
    private val category: CategoryInfo?,

    ) : PagingSource<Int, BaseFilm>() {

    override fun getRefreshKey(state: PagingState<Int, BaseFilm>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseFilm> {

        val page = params.key ?: FIRST_PAGE
        val pageSize = params.loadSize

        return kotlin.runCatching {
            withContext(Dispatchers.IO) {
                getFilms(page, category).mergeDatabase(dataBaseRepository, viewModelScope)
            }
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (it.size < pageSize) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )

    }

    private suspend fun getFilms(page: Int, category: CategoryInfo?): List<BaseFilm> {
        return when (category) {

            CategoryInfo.POPULAR -> loadPopular(networkRepository, page)
            CategoryInfo.PREMIERS -> loadPremieres(
                networkRepository,
                category.query.year,
                category.query.month
            )
            CategoryInfo.BEST -> loadBest(networkRepository, page)
            CategoryInfo.RANDOM -> loadFilmsByCounterAdnGenre(
                networkRepository,
                page,
                category.query.counterID,
                category.query.genreId
            )
            else -> emptyList()
        }
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}

