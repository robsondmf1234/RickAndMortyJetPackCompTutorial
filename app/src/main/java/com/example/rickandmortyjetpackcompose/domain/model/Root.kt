package com.example.rickandmortyjetpackcompose.domain.model

import com.google.gson.annotations.SerializedName

data class Root(
    val info: Info,
    @SerializedName("results") // Nome do campo no JSON
    val charactersInfo: List<Character>
)

