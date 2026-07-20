package com.example.rickandmortyjetpackcompose.data.repository

import com.example.rickandmortyjetpackcompose.data.api.SimpleApi
import com.example.rickandmortyjetpackcompose.data.remote.RootDTO
import com.example.rickandmortyjetpackcompose.domain.repository.CharacterRepository
import retrofit2.Response

/**
 * Implementação do repositório [CharacterRepository].
 * Responsável por decidir a fonte dos dados (neste caso, a API remota).
 */
class CharacterRepositoryImpl(private val api: SimpleApi) : CharacterRepository {

    /**
     * Realiza a chamada para a API [SimpleApi] para buscar os personagens.
     */
    override suspend fun getCharacter(): Response<RootDTO> {
        return api.getCharacters()
    }
}
