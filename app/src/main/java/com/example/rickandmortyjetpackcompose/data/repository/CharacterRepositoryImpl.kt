package com.example.rickandmortyjetpackcompose.data.repository

import com.example.rickandmortyjetpackcompose.data.api.SimpleApi
import com.example.rickandmortyjetpackcompose.data.mapper.toDomain
import com.example.rickandmortyjetpackcompose.domain.model.Root
import com.example.rickandmortyjetpackcompose.domain.repository.CharacterRepository
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

/**
 * Implementação do repositório [CharacterRepository].
 * Responsável por decidir a fonte dos dados (neste caso, a API remota).
 */
class CharacterRepositoryImpl(private val api: SimpleApi) : CharacterRepository {

    /**
     * Realiza a chamada para a API [SimpleApi] para buscar os personagens.
     */
    override suspend fun getCharacter(): Response<Root> {
        val response = api.getCharacters()
        val body = response.body()

        return if (response.isSuccessful && body != null) {
            Response.success(body.toDomain())
        } else {
            Response.error(response.code(), response.errorBody() ?: "".toResponseBody())
        }
    }
}
