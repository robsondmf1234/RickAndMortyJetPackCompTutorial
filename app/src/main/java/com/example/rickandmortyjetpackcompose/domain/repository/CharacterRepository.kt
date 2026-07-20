package com.example.rickandmortyjetpackcompose.domain.repository

import com.example.rickandmortyjetpackcompose.data.remote.Root
import retrofit2.Response

/**
 * Interface que define o contrato para o repositório de personagens.
 * Seguindo a Clean Architecture, esta interface reside na camada de Domain.
 */
interface CharacterRepository {
    /**
     * Define o método para buscar personagens.
     */
    suspend fun getCharacter(): Response<Root>
}
