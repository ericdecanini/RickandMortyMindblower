package com.example.rickandmortymindblower.data.api

import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse
}
