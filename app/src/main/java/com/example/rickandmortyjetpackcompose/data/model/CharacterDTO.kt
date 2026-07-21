package com.example.rickandmortyjetpackcompose.data.model

/**
 * Modelo de domínio que representa um personagem de Rick and Morty.
 */
data class CharacterDTO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originDTO: OriginDTO,
    val locationDTO: LocationDTO,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)