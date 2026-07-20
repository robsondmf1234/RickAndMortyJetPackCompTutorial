package com.example.rickandmortyjetpackcompose.domain.model

/**
 * Modelo de domínio que representa um personagem de Rick and Morty.
 */
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)
