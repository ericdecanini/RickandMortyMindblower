package com.example.rickandmortymindblower.fakes

import com.example.rickandmortymindblower.data.api.CharacterResponse
import com.example.rickandmortymindblower.data.api.CharactersResponse
import com.example.rickandmortymindblower.data.api.OriginResponse
import com.example.rickandmortymindblower.data.api.RickAndMortyApi

class FakeRickAndMortyApi : RickAndMortyApi {

    private val characterResponse = CharacterResponse(
        id = "1",
        name = "Rick",
        status = "Alive",
        species = "Human",
        image = "ImageUrl",
        origin = OriginResponse("C-137"),
        episode = listOf("episode/1")
    )

    override suspend fun getCharacters(): CharactersResponse {
        return CharactersResponse(results = listOf(characterResponse))
    }

    override suspend fun getCharacterById(characterId: String): CharacterResponse {
        return characterResponse
    }
}
