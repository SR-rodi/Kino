package com.example.homepage.presentation.homepage.domaine.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.all.CategoryFilms
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
    private val category: CategoryFilms?,

    ) : PagingSource<Int, BaseEntityFilm>() {

    override fun getRefreshKey(state: PagingState<Int, BaseEntityFilm>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseEntityFilm> {

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

    private suspend fun getFilms(page: Int, category: CategoryFilms?): List<BaseEntityFilm> {
        return when (category) {

            CategoryFilms.POPULAR -> loadPopular(networkRepository, page)
            CategoryFilms.PREMIERS -> loadPremieres(
                networkRepository,
                category.query.year,
                category.query.month
            )
            CategoryFilms.BEST -> loadBest(networkRepository, page)
            CategoryFilms.RANDOM -> loadFilmsByCounterAdnGenre(
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

