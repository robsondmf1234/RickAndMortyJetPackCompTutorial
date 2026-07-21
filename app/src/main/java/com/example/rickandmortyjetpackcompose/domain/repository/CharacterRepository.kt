package com.example.rickandmortyjetpackcompose.domain.repository

import com.example.rickandmortyjetpackcompose.domain.model.Character

/**
 * Interface que define o contrato para o repositório de personagens.
 * Seguindo a Clean Architecture, esta interface reside na camada de Domain.
 * Não conhece detalhes de rede, Retrofit ou serialização.
 */
interface CharacterRepository {
    /**
     * Define o método para buscar personagens.
     * Lança exceção em caso de falha (tratada nas camadas superiores).
     */
    suspend fun getCharacter(): List<Character>
}
