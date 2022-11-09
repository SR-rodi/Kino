package com.example.di

import com.example.kinopoisk_api.BASE_URL
import com.example.kinopoisk_api.domine.api.FilmsApi
import com.example.kinopoisk_api.domine.repo.NetworkRepository
import com.example.kinopoisk_api.domine.repo.PagingRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val filmsApi = module {

    single(named("retrofit")) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    single {
        val retrofit = get<Retrofit>(named("retrofit"))
        retrofit.create(FilmsApi::class.java)
    }
}

val networkRepository =  module {
    single { NetworkRepository(filmsApi = get()) }

    single { PagingRepository(get()) }

}