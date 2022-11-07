package com.example.kinopoisktest.app

import android.app.Application
import com.example.di.filmsApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppComponent:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(listOf(filmsApi))
        }
    }

}