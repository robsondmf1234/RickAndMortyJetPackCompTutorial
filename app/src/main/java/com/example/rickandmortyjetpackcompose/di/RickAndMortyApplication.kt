package com.example.rickandmortyjetpackcompose.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Classe de Application customizada.
 * Responsável por inicializar frameworks globais, como o Koin para Injeção de Dependência.
 */
class RickAndMortyApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Inicializa o Koin.
        startKoin {
            // Fornece o contexto do Android para o Koin.
            androidContext(this@RickAndMortyApplication)
            
            // Carrega os módulos de dependências definidos.
            modules(appModule)
        }
    }
}
