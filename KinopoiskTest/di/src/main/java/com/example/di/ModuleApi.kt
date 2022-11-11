package com.example.di

import com.example.kinopoisk_api.BASE_URL
import com.example.kinopoisk_api.api.CategoryFilmsApi
import com.example.kinopoisk_api.api.DetailFilmsApi
import com.example.kinopoisk_api.api.DetailStaffApi
import com.example.kinopoisk_api.paging.PagingRepository
import com.example.kinopoisk_api.repository_ipl.NetworkDetailsRepositoryImpl
import com.example.kinopoisk_api.repository_ipl.NetworkCategoryImpl
import com.example.kinopoisk_api.repository_ipl.NetworkStaffRepositoryImpl
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

}

val networkRepository = module {

    single { PagingRepository(get()) }

    single { NetworkCategoryImpl(get()) }

    single { NetworkDetailsRepositoryImpl(get()) }

    single { NetworkStaffRepositoryImpl(get()) }

}