package com.example.rickandmortymindblower.fakes

import com.example.rickandmortymindblower.data.repo.CharactersRepository
import com.example.rickandmortymindblower.entity.Character

class FakeCharactersRepository : CharactersRepository {

    override suspend fun getCharacters(): List<Character> {
        return listOf(Character(
            id = "1",
            name = "Rick",
            status = "Alive",
            species = "Human",
            image = "ImageUrl",
            origin = "C-137",
            episodeDebut = "1",
        ))
    }

    override suspend fun getCharacterById(characterId: String): Character {
        return Character(
            id = "1",
            name = "Rick",
            status = "Alive",
            species = "Human",
            image = "ImageUrl",
            origin = "C-137",
            episodeDebut = "1",
        )
    }
}