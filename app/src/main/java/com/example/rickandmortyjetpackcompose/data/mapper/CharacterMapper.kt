package com.example.rickandmortyjetpackcompose.data.mapper

import com.example.rickandmortyjetpackcompose.data.model.CharacterDTO
import com.example.rickandmortyjetpackcompose.data.model.InfoDTO
import com.example.rickandmortyjetpackcompose.data.model.LocationDTO
import com.example.rickandmortyjetpackcompose.data.model.OriginDTO
import com.example.rickandmortyjetpackcompose.data.model.RootDTO
import com.example.rickandmortyjetpackcompose.domain.model.Character
import com.example.rickandmortyjetpackcompose.domain.model.Info
import com.example.rickandmortyjetpackcompose.domain.model.Location
import com.example.rickandmortyjetpackcompose.domain.model.Origin
import com.example.rickandmortyjetpackcompose.domain.model.Root

fun RootDTO.toDomain(): Root = Root(
    info = info.toDomain(),
    charactersInfo = charactersInfo.map { it.toDomain() }
)

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

fun OriginDTO.toDomain(): Origin = Origin(
    name = name,
    url = url
)

fun LocationDTO.toDomain(): Location = Location(
    name = name,
    url = url
)

fun InfoDTO.toDomain(): Info = Info(
    count = count,
    pages = pages,
    next = next,
    prev = prev
)

