package com.example.rickandmortyjetpackcompose.data.remote

import com.example.rickandmortyjetpackcompose.data.model.CharacterDTO
import com.example.rickandmortyjetpackcompose.data.model.InfoDTO
import com.google.gson.annotations.SerializedName

/**
 * Objeto de resposta raiz da API de Rick and Morty.
 * Contém informações de paginação e a lista de resultados.
 */
data class RootDTO(
    val info: InfoDTO,

    /**
     * Lista de personagens retornada pela API. 
     * Mapeada do campo "results" no JSON.
     */
    @SerializedName("results")
    val charactersInfo: List<CharacterDTO>
)
