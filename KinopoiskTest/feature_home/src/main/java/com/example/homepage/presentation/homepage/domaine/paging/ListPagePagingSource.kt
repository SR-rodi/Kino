package com.example.homepage.presentation.homepage.domaine.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.all.BaseFilms
import com.example.homepage.presentation.homepage.domaine.NetworkCategoryRepository
import com.example.homepage.presentation.homepage.tools.loadBest
import com.example.homepage.presentation.homepage.tools.loadFilmsByCounterAdnGenre
import com.example.homepage.presentation.homepage.tools.loadPopular
import com.example.homepage.presentation.homepage.tools.loadPremieres

class ListPagePagingSource(
    private val networkRepository: NetworkCategoryRepository,
    private val category: CategoryFilms,

    ) : PagingSource<Int, BaseFilms>() {

    override fun getRefreshKey(state: PagingState<Int, BaseFilms>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseFilms> {

        val page = params.key ?: FIRST_PAGE
        val pageSize = params.loadSize

        val films = getFilms(page, category)

        return LoadResult.Page(
            data = films,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (films.size<pageSize) null else page+1
        )
    }

    private suspend fun getFilms(page: Int, category: CategoryFilms): List<BaseFilms> {
        return when (category) {

            CategoryFilms.POPULAR -> loadPopular(networkRepository, page)
            CategoryFilms.PREMIERS -> loadPremieres(
                networkRepository,
                category.query.year,
                category.query.month
            )
            CategoryFilms.BEST -> loadBest(networkRepository, page)
            CategoryFilms.RANDOM -> loadFilmsByCounterAdnGenre(
                networkRepository, page, category.query.counterID, category.query.genreId
            )
        }


    }

    companion object {
        const val FIRST_PAGE = 1
    }
}

