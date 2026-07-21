package com.example.rickandmortyjetpackcompose.data.model

import com.google.gson.annotations.SerializedName

/**
 * DTO raiz da resposta da API, contendo paginação e lista de personagens.
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