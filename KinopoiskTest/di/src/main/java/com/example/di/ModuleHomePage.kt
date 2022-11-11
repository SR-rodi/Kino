package com.example.di

import com.example.homepage.presentation.homepage.HomeViewModel
import com.example.homepage.presentation.categoryoage.CategoryPageViewModel
import com.example.homepage.presentation.films_ditails.FilmInfoViewModel
import com.example.homepage.presentation.persone_details.StaffInfoViewModel

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
