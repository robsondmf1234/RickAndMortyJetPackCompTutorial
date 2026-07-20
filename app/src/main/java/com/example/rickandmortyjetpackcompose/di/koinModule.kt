package com.example.rickandmortyjetpackcompose.di

import com.example.rickandmortyjetpackcompose.BuildConfig
import com.example.rickandmortyjetpackcompose.data.api.SimpleApi
import com.example.rickandmortyjetpackcompose.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyjetpackcompose.domain.repository.CharacterRepository
import com.example.rickandmortyjetpackcompose.presentation.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    // Repository como singleton
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }

    // ViewModel com injeção de Repository
    viewModel { MainViewModel(get()) }


    //Logger interceptor
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    //OkHttp como singleton
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    //Retrofit como singleton
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //SimpleApi como singleton(depende do retrofit)
    single { get<Retrofit>().create(SimpleApi::class.java) }
}
