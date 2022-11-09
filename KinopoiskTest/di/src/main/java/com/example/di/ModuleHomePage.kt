package com.example.di

import com.example.homepage.presentation.HomeViewModel
import com.example.screen_listpage.CategoryPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {

    viewModel<HomeViewModel>{
        HomeViewModel( get())
    }

    viewModel{
        CategoryPageViewModel(get())
    }
}
