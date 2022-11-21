package com.example.di

import com.example.feature_details.presentation.filmographi.viewModel.FilmographyListViewModel
import com.example.feature_details.presentation.filmographi.viewModel.FilmographyViewModel
import com.example.feature_details.presentation.gallery.ListGalleryViewModel
import com.example.homepage.presentation.homepage.presentation.home_fragment.HomeViewModel
import com.example.homepage.presentation.homepage.presentation.category_fragment.CategoryPageViewModel
import com.example.feature_details.presentation.films_ditails.FilmInfoViewModel
import com.example.feature_details.presentation.gallery.GalleryViewModel
import com.example.feature_details.presentation.staff_details.viewModel.StaffInfoViewModel
import com.example.screen_listpage.start.SearchStartViewModel

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
        FilmInfoViewModel(get(),get())
    }

    viewModel {
        StaffInfoViewModel(get(),get(),get())
    }

    viewModel{
        ListGalleryViewModel(get(),get())
    }

    viewModel{
        GalleryViewModel(get(),get())
    }

    viewModel{
        FilmographyListViewModel(get())
    }

    viewModel{
        FilmographyViewModel(get(),get())
    }

    viewModel{
        SearchStartViewModel(get(),get())
    }
}
