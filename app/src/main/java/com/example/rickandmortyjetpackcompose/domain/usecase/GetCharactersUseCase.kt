package com.example.rickandmortyjetpackcompose.domain.usecase

import com.example.rickandmortyjetpackcompose.domain.model.Character
import com.example.rickandmortyjetpackcompose.domain.repository.CharacterRepository

/**
 * Caso de uso responsável por buscar a lista de personagens.
 * Atua como intermediário entre a [presentation] e o [CharacterRepository],
 * centralizando a regra de negócio de "obter personagens".
 */
class GetCharactersUseCase(private val repository: CharacterRepository) {

    /**
     * Executa o caso de uso.
     * Delega a busca ao repositório e retorna a lista de personagens do domínio.
     * Lança exceção em caso de falha (tratada pela camada de apresentação).
     */
    suspend operator fun invoke(): List<Character> {
        return repository.getCharacter()
    }
}