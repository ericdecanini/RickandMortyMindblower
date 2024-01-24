package com.example.rickandmortymindblower.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse

    @GET("character/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
    ): CharacterResponse
}
