package com.example.kinopoisk_api.domine.repo

import android.graphics.pdf.PdfDocument.Page
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kinopoisk_api.CategoryFilms
import com.example.kinopoisk_api.domine.api.FilmsApi
import com.example.kinopoisk_api.domine.home_page.HomePageItem
import kotlinx.coroutines.flow.Flow

class PagingRepository(
    private val networkRepository: NetworkRepository
) {
    fun getFlowFilms(category: CategoryFilms): Flow<PagingData<HomePageItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ListPagePagingSource(networkRepository,category) }
        ).flow
    }
}