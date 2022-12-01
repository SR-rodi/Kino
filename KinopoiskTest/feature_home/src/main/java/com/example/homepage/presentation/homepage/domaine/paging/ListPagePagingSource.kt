package com.example.homepage.presentation.homepage.domaine.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.category.BaseCategory
import com.example.core.tools.base_model.category.StartCategory
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
    private val category: BaseCategory?,

    ) : PagingSource<Int, NestedInfoInCategory>() {

    override fun getRefreshKey(state: PagingState<Int, NestedInfoInCategory>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NestedInfoInCategory> {

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

    private suspend fun getFilms(page: Int, category: BaseCategory?): List<BaseFilm> {
        category as StartCategory
        Log.e("Kart","год ${category.query?.year} месяц ${category.query?.month}")
        return when (category.category) {

            CategoryInfo.POPULAR -> loadPopular(networkRepository, page)
            CategoryInfo.PREMIERS -> loadPremieres(
                networkRepository,
                category.query!!.year,
                category.query!!.month
            )
            CategoryInfo.BEST -> loadBest(networkRepository, page)
            CategoryInfo.RANDOM -> loadFilmsByCounterAdnGenre(
                networkRepository,
                page,
                category.query!!.counterID,
                category.query!!.genreId
            )
            else -> emptyList()
        }
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}

