package com.example.rickandmortyjetpackcompose.domain.model

/**
 * Entidade de domínio que representa um personagem.
 * Não contém detalhes de rede, serialização ou framework.
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