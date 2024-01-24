package com.example.rickandmortymindblower.repo

import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
) {

    suspend fun getCharacters() : List<Character> {
        return rickAndMortyApi.getCharacters().results
    }
}
