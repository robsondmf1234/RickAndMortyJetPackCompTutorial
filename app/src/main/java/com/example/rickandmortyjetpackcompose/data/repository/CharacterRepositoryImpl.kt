package com.example.rickandmortyjetpackcompose.data.repository

import com.example.rickandmortyjetpackcompose.data.api.SimpleApi
import com.example.rickandmortyjetpackcompose.data.remote.Root
import com.example.rickandmortyjetpackcompose.domain.model.Character
import com.example.rickandmortyjetpackcompose.domain.repository.CharacterRepository
import retrofit2.Response

class CharacterRepositoryImpl(private val api: SimpleApi) : CharacterRepository {

    override suspend fun getCharacter(): Response<Root> {
        return api.getCharacters()
    }
}