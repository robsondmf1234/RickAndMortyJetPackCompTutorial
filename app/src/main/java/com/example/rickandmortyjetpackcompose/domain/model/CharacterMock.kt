package com.example.rickandmortyjetpackcompose.domain.model

import com.example.rickandmortyjetpackcompose.data.model.CharacterDTO
import com.example.rickandmortyjetpackcompose.data.model.LocationDTO
import com.example.rickandmortyjetpackcompose.data.model.OriginDTO

/**
 * Objeto utilitário contendo dados de exemplo (mocks) para a classe Character.
 * Útil para Previews de Compose e Testes Unitários.
 */
object CharacterMock {

    /**
     * Retorna um único personagem mockado (Rick Sanchez).
     */
    val mockCharacter = CharacterDTO(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        originDTO = OriginDTO(
            name = "Earth (C-137)",
            url = "https://rickandmortyapi.com/api/location/1"
        ),
        locationDTO = LocationDTO(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2"
        ),
        url = "https://rickandmortyapi.com/api/character/1",
        created = "2017-11-04T18:48:46.250Z"
    )

    /**
     * Retorna uma lista de personagens mockados.
     */
    val mockCharacterList = listOf(
        mockCharacter,
        CharacterDTO(
            id = 2,
            name = "Morty Smith",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            originDTO = OriginDTO(name = "unknown", url = ""),
            locationDTO = LocationDTO(
                name = "Citadel of Ricks",
                url = "https://rickandmortyapi.com/api/location/3"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/2",
            created = "2017-11-04T18:50:21.651Z"
        ),
        CharacterDTO(
            id = 3,
            name = "Summer Smith",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Female",
            originDTO = OriginDTO(
                name = "Earth (Replacement Dimension)",
                url = "https://rickandmortyapi.com/api/location/20"
            ),
            locationDTO = LocationDTO(
                name = "Earth (Replacement Dimension)",
                url = "https://rickandmortyapi.com/api/location/20"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/6"),
            url = "https://rickandmortyapi.com/api/character/3",
            created = "2017-11-04T19:09:56.428Z"
        )
    )
}
