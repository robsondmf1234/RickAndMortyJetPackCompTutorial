package com.example.rickandmortyjetpackcompose.domain.repository

import com.example.rickandmortyjetpackcompose.data.remote.Root
import retrofit2.Response

interface CharacterRepository {
    suspend fun getCharacter(): Response<Root>
}