package com.example.rickandmortyjetpackcompose.domain.model

/**
 * Modelo de domínio agregado da listagem de personagens.
 * Reúne metadados de paginação e os personagens carregados.
 */
data class CharacterResponse(
    val pageInfo: PageInfo,

    /**
     * Lista de personagens no formato de domínio.
     */
    val charactersInfo: List<Character>
)