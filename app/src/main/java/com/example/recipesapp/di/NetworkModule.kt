package com.example.recipesapp.di

import com.example.recipesapp.data.network.ApiKeyInterceptor
import com.example.recipesapp.data.network.ApiService
import com.example.recipesapp.util.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }

    // Api Service
    /*single<ApiService> {
        NetworkClient(get())
            .getClient()
            .create(ApiService::class.java)
    }*/

    /*val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()*/
}