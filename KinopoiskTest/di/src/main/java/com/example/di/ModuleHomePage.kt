package com.example.di

import com.example.homepage.presentation.homepage.presentation.home_fragment.HomeViewModel
import com.example.homepage.presentation.homepage.presentation.category_fragment.CategoryPageViewModel
import com.example.feature_details.films_ditails.FilmInfoViewModel
import com.example.feature_details.persone_details.StaffInfoViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        CategoryPageViewModel(get())
    }

    viewModel {
        FilmInfoViewModel(get())
    }

    viewModel {
        StaffInfoViewModel(get())
    }
}
