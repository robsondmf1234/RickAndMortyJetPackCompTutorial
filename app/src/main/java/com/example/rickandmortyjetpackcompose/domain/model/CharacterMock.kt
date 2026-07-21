package com.example.rickandmortyjetpackcompose.domain.model

/**
 * Objeto utilitário contendo dados de exemplo (mocks) para a classe Character.
 * Útil para Previews de Compose e Testes Unitários.
 */
object CharacterMock {

    /**
     * Retorna um único personagem mockado (Rick Sanchez).
     */
    val mockCharacter = Character(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Origin(
            name = "Earth (C-137)",
            url = "https://rickandmortyapi.com/api/location/1"
        ),
        location = Location(
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
        Character(
            id = 2,
            name = "Morty Smith",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = Origin(name = "unknown", url = ""),
            location = Location(
                name = "Citadel of Ricks",
                url = "https://rickandmortyapi.com/api/location/3"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/2",
            created = "2017-11-04T18:50:21.651Z"
        ),
        Character(
            id = 3,
            name = "Summer Smith",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Female",
            origin = Origin(
                name = "Earth (Replacement Dimension)",
                url = "https://rickandmortyapi.com/api/location/20"
            ),
            location = Location(
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
