package com.example.rickandmortyjetpackcompose.data.model

/**
 * DTO da camada data com metadados de paginação da API.
 */
data class InfoDTO(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)