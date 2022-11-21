package com.example.di

import androidx.lifecycle.SavedStateHandle
import com.example.core.tools.BASE_URL
import com.example.homepage.presentation.homepage.domaine.CategoryFilmsApi
import com.example.feature_details.domein.DetailFilmsApi
import com.example.feature_details.domein.DetailStaffApi
import com.example.feature_details.domein.repository_ipl.FilmUseCase
import com.example.feature_details.domein.repository_ipl.GalleryUseCase
import com.example.homepage.presentation.homepage.domaine.paging.PagingRepository
import com.example.feature_details.domein.repository_ipl.NetworkFilmDetailsRepositoryImpl
import com.example.homepage.presentation.homepage.domaine.NetworkCategoryImpl
import com.example.feature_details.domein.repository_ipl.NetworkStaffRepositoryImpl
import com.example.screen_listpage.network.SearchApi
import com.example.screen_listpage.network.SearchPagingRepo
import com.example.screen_listpage.network.SearchRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val filmsApi = module {

    single(named("retrofit")) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    single<CategoryFilmsApi> {
        get<Retrofit>(named("retrofit")).create()
    }

    single<DetailFilmsApi> {
        get<Retrofit>(named("retrofit")).create()
    }

    single<DetailStaffApi> {
        get<Retrofit>(named("retrofit")).create()
    }

    single<SearchApi> {
        get<Retrofit>(named("retrofit")).create()
    }

    single{
        SavedStateHandle()
    }

}

val networkRepository = module {

    single { PagingRepository(get()) }

    single { SearchPagingRepo(get()) }

    single { NetworkCategoryImpl(get()) }

    single { NetworkFilmDetailsRepositoryImpl(get()) }

    single { NetworkStaffRepositoryImpl(get()) }

    single { SearchRepositoryImpl(get()) }

}

val useCase = module {

    single { FilmUseCase(get()) }

    single { GalleryUseCase(get()) }
}