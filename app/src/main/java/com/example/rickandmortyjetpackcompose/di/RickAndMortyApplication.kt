package com.example.rickandmortyjetpackcompose.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyApplication(): Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyApplication)
            modules(appModule)
        }
    }
}