package com.example.rickandmortyjetpackcompose.domain.model

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
    val charactersInfo: List<Character>
)