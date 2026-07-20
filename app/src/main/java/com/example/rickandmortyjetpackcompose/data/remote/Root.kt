package com.example.rickandmortyjetpackcompose.data.remote

import com.example.rickandmortyjetpackcompose.domain.model.Character
import com.example.rickandmortyjetpackcompose.domain.model.Info
import com.google.gson.annotations.SerializedName

/**
 * Objeto de resposta raiz da API de Rick and Morty.
 * Contém informações de paginação e a lista de resultados.
 */
data class Root(
    val info: Info,
    
    /**
     * Lista de personagens retornada pela API. 
     * Mapeada do campo "results" no JSON.
     */
    @SerializedName("results")
    val charactersInfo: List<Character>
)
