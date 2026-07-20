package com.example.rickandmortyjetpackcompose.data.api

import com.example.rickandmortyjetpackcompose.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = BuildConfig.BASE_URL

    val api: SimpleApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SimpleApi::class.java)
    }
}
