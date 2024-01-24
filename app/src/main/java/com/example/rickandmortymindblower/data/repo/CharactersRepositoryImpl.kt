package com.example.rickandmortymindblower.data.repo

import com.example.rickandmortymindblower.data.api.CharacterResponse
import com.example.rickandmortymindblower.data.api.RickAndMortyApi
import com.example.rickandmortymindblower.data.db.FavouritesDao
import com.example.rickandmortymindblower.entity.Character
import javax.inject.Inject

interface CharactersRepository {

    suspend fun getCharacters(): List<Character>

    suspend fun getCharacterById(characterId: String): Character

    suspend fun getFavouriteCharacters(): List<Character>
}

class CharactersRepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val favouritesDao: FavouritesDao,
) : CharactersRepository {

    override suspend fun getCharacters(): List<Character> {
        return try {
            rickAndMortyApi.getCharacters().results.map {
                mapCharacterResponseToCharacter(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Error while trying to get characters")
        }

    }

    private fun mapCharacterResponseToCharacter(response: CharacterResponse): Character {
        return Character(
            id = response.id,
            name = response.name,
            status = response.status,
            species = response.species,
            image = response.image,
            origin = response.origin.name,
            episodeDebut = response.episode.firstOrNull()?.substringAfterLast("/").orEmpty()
        )
    }

    override suspend fun getCharacterById(characterId: String): Character {
        val response = rickAndMortyApi.getCharacterById(characterId)
        return mapCharacterResponseToCharacter(response)
    }

    override suspend fun getFavouriteCharacters(): List<Character> {
        TODO("Not yet implemented")
    }
}
