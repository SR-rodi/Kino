package com.example.kinopoisktest.app

import android.app.Application
import com.example.di.filmsApi
import com.example.di.networkRepository
import com.example.di.viewModel
import org.koin.core.context.startKoin

class AppComponent:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(listOf(filmsApi, viewModel, networkRepository))
        }
    }

}