package com.example.kinopoisk_api.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk_api.loadBest
import com.example.kinopoisk_api.loadFilmsByCounterAdnGenre
import com.example.kinopoisk_api.loadPopular
import com.example.kinopoisk_api.loadPremieres
import com.example.data.home_page.all.CategoryFilms
import com.example.data.home_page.films.BaseFilms
import com.example.kinopoisk_api.repo.NetworkCategoryRepository

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

