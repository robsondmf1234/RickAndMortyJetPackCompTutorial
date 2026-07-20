package com.example.rickandmortyjetpackcompose.data.repository

import com.example.rickandmortyjetpackcompose.domain.model.Root
import retrofit2.Response

interface CharacterRepository {
    suspend fun getCharacter(): Response<Root>
}