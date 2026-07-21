package com.example.rickandmortyjetpackcompose.data.model

/**
 * DTO da camada data que representa um personagem retornado pela API.
 */
data class CharacterDTO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginDTO,
    val location: LocationDTO,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)