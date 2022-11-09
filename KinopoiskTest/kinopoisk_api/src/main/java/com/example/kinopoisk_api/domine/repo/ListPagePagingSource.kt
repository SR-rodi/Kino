package com.example.kinopoisk_api.domine.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk_api.*
import com.example.kinopoisk_api.domine.home_page.HomePageItem
import com.example.kinopoisk_api.domine.home_page.films.FilmsDTO

class ListPagePagingSource(
    private val networkRepository: NetworkRepository,
    private val category: CategoryFilms,

    ) : PagingSource<Int, HomePageItem>() {

    override fun getRefreshKey(state: PagingState<Int, HomePageItem>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomePageItem> {

        val page = params.key ?: FIRST_PAGE
        val films = networkRepository.getBestFilmsInNetwork(page)

        getFilms(page, category)

        return LoadResult.Page(
            data = films,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (films.isEmpty()) null else page
        )
    }

    private suspend fun getFilms(page: Int, category: CategoryFilms): List<HomePageItem> {
        return when (category) {

            CategoryFilms.POPULAR -> loadPopular(networkRepository, page)
            CategoryFilms.PREMIERS -> loadPremieres(networkRepository, 1, "mart")
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

class Query(
    val year: Int = 2022,
    val month: String = "",
    val counterID: Int = 2022,
    val genreId: Int = 2022,
)
