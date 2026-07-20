package com.example.rickandmortyjetpackcompose.data.remote

import com.example.rickandmortyjetpackcompose.domain.model.Character
import com.example.rickandmortyjetpackcompose.domain.model.Info
import com.google.gson.annotations.SerializedName

data class Root(
    val info: Info,
    @SerializedName("results") // Nome do campo no JSON
    val charactersInfo: List<Character>
)