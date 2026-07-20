package com.example.rickandmortyjetpackcompose.data.api

import com.example.rickandmortyjetpackcompose.data.remote.Root
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface que define os endpoints da API de Rick and Morty utilizando Retrofit.
 */
interface SimpleApi {

    /**
     * Busca a lista de personagens.
     * Retorna um objeto [Response] contendo o [Root] com as informações e resultados.
     */
    @GET("character")
    suspend fun getCharacters(): Response<Root>
}
