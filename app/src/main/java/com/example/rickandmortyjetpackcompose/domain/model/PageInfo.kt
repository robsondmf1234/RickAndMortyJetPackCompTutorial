package com.example.rickandmortyjetpackcompose.domain.model

/**
 * Entidade de domínio com metadados de paginação da listagem de personagens.
 */
data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)