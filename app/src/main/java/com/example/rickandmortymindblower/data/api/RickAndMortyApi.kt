package com.example.rickandmortymindblower.data.api

import com.example.rickandmortymindblower.entity.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse

    @GET("character/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
    ): Character
}
