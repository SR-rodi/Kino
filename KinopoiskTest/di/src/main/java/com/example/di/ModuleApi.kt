package com.example.di

import com.example.core.tools.BASE_URL
import com.example.homepage.presentation.homepage.domaine.CategoryFilmsApi
import com.example.feature_details.DetailFilmsApi
import com.example.feature_details.DetailStaffApi
import com.example.homepage.presentation.homepage.domaine.paging.PagingRepository
import com.example.feature_details.repository_ipl.NetworkDetailsRepositoryImpl
import com.example.homepage.presentation.homepage.domaine.NetworkCategoryImpl
import com.example.feature_details.repository_ipl.NetworkStaffRepositoryImpl
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