package com.example.screen_listpage.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.screen_listpage.data.Film
import com.example.screen_listpage.data.SetSearch

class SearchPagingSource(
   private val networkRepositoryImpl: SearchRepositoryImpl,
   private val keyWord:String
) : PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val page = params.key ?: FIRST_PAGE
        val pageSize = params.loadSize

        val films = networkRepositoryImpl.getSearchFilms(SetSearch, keyWord,page).items

        return LoadResult.Page(
            data = films,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (films.size<pageSize) null else page+1
        )
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}