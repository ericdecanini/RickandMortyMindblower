package com.example.rickandmortymindblower.data.api

data class CharactersResponse(
    val results: List<CharacterResponse>
)

data class CharacterResponse(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val origin: OriginResponse,
    val episode: List<String>,
)

data class OriginResponse(val name: String)
