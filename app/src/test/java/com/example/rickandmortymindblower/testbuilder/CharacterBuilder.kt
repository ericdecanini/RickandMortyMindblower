package com.example.rickandmortymindblower.testbuilder

import com.example.rickandmortymindblower.entity.Character

object CharacterBuilder {

    fun aCharacter(
        id: String = "1",
        name: String = "Rick",
        status: String = "Alive",
        species: String = "Human",
        image: String = "ImageUrl",
        origin: String = "C-137",
        episodeDebut: String = "1",
        isFavourite: Boolean = false,
    ) = Character(
        id = id,
        name = name,
        status = status,
        species = species,
        image = image,
        origin = origin,
        episodeDebut = episodeDebut,
        isFavourite = isFavourite,
    )
}
