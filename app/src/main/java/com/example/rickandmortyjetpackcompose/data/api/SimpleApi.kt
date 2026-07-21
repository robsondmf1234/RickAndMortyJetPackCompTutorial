package com.example.rickandmortyjetpackcompose.data.api

import com.example.rickandmortyjetpackcompose.data.model.RootDTO
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface de rede (API) - Camada DATA.
 * Sua única responsabilidade é definir como conversar com o servidor.
 */
interface SimpleApi {

    @GET("character")
    suspend fun getCharacters(): Response<RootDTO>
}