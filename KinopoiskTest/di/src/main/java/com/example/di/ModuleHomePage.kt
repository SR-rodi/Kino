package com.example.di

import com.example.homepage.data.repository.NetworkRepositoryHomePage
import com.example.homepage.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
val homePageModel = module {
    single { NetworkRepositoryHomePage(filmsApi = get()) }

    viewModel<HomeViewModel>{
        HomeViewModel(networkRepository =  get())
    }
}*/
