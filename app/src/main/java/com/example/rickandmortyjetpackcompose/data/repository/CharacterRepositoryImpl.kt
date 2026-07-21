package com.example.rickandmortyjetpackcompose.data.repository

import com.example.rickandmortyjetpackcompose.data.api.SimpleApi
import com.example.rickandmortyjetpackcompose.data.mapper.toDomain
import com.example.rickandmortyjetpackcompose.domain.model.Character
import com.example.rickandmortyjetpackcompose.domain.repository.CharacterRepository

/**
 * Implementação do repositório [CharacterRepository].
 * Responsável por decidir a fonte dos dados (neste caso, a API remota).
 * Toda interação com Retrofit e DTOs fica encapsulada aqui.
 */
class CharacterRepositoryImpl(private val api: SimpleApi) : CharacterRepository {

    /**
     * Realiza a chamada para a API [SimpleApi], aplica o mapper e retorna
     * a lista de personagens como modelos de domínio.
     * Lança exceção em caso de resposta mal-sucedida ou body nulo.
     */
    override suspend fun getCharacter(): List<Character> {
        val response = api.getCharacters()
        val body = response.body()

        if (!response.isSuccessful || body == null) {
            throw Exception("Erro ao buscar personagens: HTTP ${response.code()}")
        }

        return body.toDomain().charactersInfo
    }
}
