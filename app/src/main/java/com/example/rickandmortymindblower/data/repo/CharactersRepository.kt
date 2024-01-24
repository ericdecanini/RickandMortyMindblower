package com.example.rickandmortymindblower.data.repo

import com.example.rickandmortymindblower.data.api.RickAndMortyApi
import com.example.rickandmortymindblower.entity.Character
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
) {

    suspend fun getCharacters(): List<Character> {
        return rickAndMortyApi.getCharacters().results
    }

    suspend fun getCharacterById(characterId: String): Character {
        return rickAndMortyApi.getCharacterById(characterId)
    }
}
