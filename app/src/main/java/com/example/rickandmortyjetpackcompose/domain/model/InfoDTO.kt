package com.example.rickandmortyjetpackcompose.domain.model

/**
 * Modelo de domínio que contém informações de paginação da API.
 */
data class InfoDTO(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
