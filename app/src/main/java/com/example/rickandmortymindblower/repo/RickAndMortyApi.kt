package com.example.rickandmortymindblower.repo

import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse
}
