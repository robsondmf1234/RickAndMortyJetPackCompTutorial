package com.example.rickandmortyjetpackcompose.di

import com.example.rickandmortyjetpackcompose.BuildConfig
import com.example.rickandmortyjetpackcompose.data.api.CharacterApi
import com.example.rickandmortyjetpackcompose.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyjetpackcompose.domain.repository.CharacterRepository
import com.example.rickandmortyjetpackcompose.presentation.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Módulo de Dependências do Koin.
 * Aqui definimos como cada classe do sistema deve ser instanciada e como
 * elas se relacionam (quem depende de quem).
 */
val appModule = module {

    // --- Camada de Dados (Data) ---

    // Define o interceptor de logs para o OkHttp. 
    // O nível BODY permite ver no Logcat todo o conteúdo das requisições e respostas JSON.
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // Define o OkHttpClient como Singleton.
    // O 'get()' aqui busca automaticamente o HttpLoggingInterceptor definido acima.
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Configuração do Retrofit como Singleton.
    // Utiliza a BASE_URL vinda do BuildConfig e o OkHttpClient injetado via 'get()'.
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provê a interface SimpleApi criada pelo Retrofit.
    // Essencial para que o Repositório consiga realizar as chamadas de rede.
    single { get<Retrofit>().create(CharacterApi::class.java) }

    // --- Camada de Domínio / Repositório ---

    // Provê a implementação do Repositório.
    // Declaramos como 'CharacterRepository' (interface) para manter o desacoplamento.
    // Injeta a 'SimpleApi' necessária para o funcionamento do repositório.
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }

    // --- Camada de Apresentação (Presentation) ---

    // Define o ViewModel.
    // Usamos 'viewModel' em vez de 'single' para que o Koin respeite o ciclo de vida do Android.
    // Injeta automaticamente o 'CharacterRepository' no construtor do ViewModel.
    viewModel { MainViewModel(get()) }
}
