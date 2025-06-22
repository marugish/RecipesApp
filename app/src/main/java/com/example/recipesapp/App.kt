package com.example.recipesapp

import android.app.Application
import com.example.recipesapp.di.interactorModule
import com.example.recipesapp.di.networkModule
import com.example.recipesapp.di.repositoryModule
import com.example.recipesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoryModule, interactorModule, viewModelModule)
        }

    }
}