package com.example.rickandmortyjetpackcompose.data.api

import com.example.rickandmortyjetpackcompose.domain.model.Root
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    // TODO: avaliar se endpoint "api" será usado no futuro

    @GET("character")
    suspend fun getCharacters(): Response<Root>
}