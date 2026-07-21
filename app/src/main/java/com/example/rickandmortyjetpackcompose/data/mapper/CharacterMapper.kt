package com.example.rickandmortyjetpackcompose.data.mapper

import com.example.rickandmortyjetpackcompose.data.model.CharacterDTO
import com.example.rickandmortyjetpackcompose.data.model.InfoDTO
import com.example.rickandmortyjetpackcompose.data.model.LocationDTO
import com.example.rickandmortyjetpackcompose.data.model.OriginDTO
import com.example.rickandmortyjetpackcompose.data.model.RootDTO
import com.example.rickandmortyjetpackcompose.domain.model.Character
import com.example.rickandmortyjetpackcompose.domain.model.PageInfo
import com.example.rickandmortyjetpackcompose.domain.model.Location
import com.example.rickandmortyjetpackcompose.domain.model.Origin
import com.example.rickandmortyjetpackcompose.domain.model.CharacterResponse

/**
 * Mapeadores da camada Data para converter DTOs em modelos de domínio.
 * Assim, DTOs não vazam para as camadas Domain/Presentation.
 */

/** Converte a resposta raiz da API em resposta de domínio. */
fun RootDTO.toDomain(): CharacterResponse = CharacterResponse(
    pageInfo = info.toDomain(),
    charactersInfo = charactersInfo.map { it.toDomain() }
)

/** Converte um personagem DTO em entidade de domínio. */
fun CharacterDTO.toDomain(): Character = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.toDomain(),
    location = location.toDomain(),
    image = image,
    episode = episode,
    url = url,
    created = created
)

/** Converte a origem do personagem para o modelo de domínio. */
fun OriginDTO.toDomain(): Origin = Origin(
    name = name,
    url = url
)

/** Converte a localização do personagem para o modelo de domínio. */
fun LocationDTO.toDomain(): Location = Location(
    name = name,
    url = url
)

/** Converte metadados de paginação da API para o domínio. */
fun InfoDTO.toDomain(): PageInfo = PageInfo(
    count = count,
    pages = pages,
    next = next,
    prev = prev
)
