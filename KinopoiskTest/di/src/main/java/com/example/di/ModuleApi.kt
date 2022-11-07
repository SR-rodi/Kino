package com.example.di

import com.example.core.BASE_URL
import com.example.homepage.data.repository.NetworkRepositoryHomePage
import com.example.homepage.presentation.HomeViewModel
import com.example.kinopoisk_api.FilmsApi
import com.example.kinopoisk_api.createFilmsApi
import org.koin.androidx.viewmodel.dsl.viewModel
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

    single {
        val retrofit = get<Retrofit>(named("retrofit"))
        retrofit.create(FilmsApi::class.java)
    }


    single { NetworkRepositoryHomePage(filmsApi = get()) }

    viewModel<HomeViewModel>{
        HomeViewModel(networkRepository =  get())
    }
}