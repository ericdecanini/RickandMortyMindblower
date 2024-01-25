package com.example.rickandmortymindblower.data.repo

import com.example.rickandmortymindblower.data.api.CharacterResponse
import com.example.rickandmortymindblower.data.api.RickAndMortyApi
import com.example.rickandmortymindblower.data.db.FavouriteEntity
import com.example.rickandmortymindblower.data.db.FavouritesDao
import com.example.rickandmortymindblower.entity.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CharactersRepository {

    suspend fun getCharacters(): List<Character>

    suspend fun getCharacterById(characterId: String): Character

    fun observeFavouriteCharacters(): Flow<List<Character>>

    suspend fun addFavouriteCharacter(character: Character)

    suspend fun removeFavouriteCharacter(character: Character)
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

    override fun observeFavouriteCharacters(): Flow<List<Character>> {
        return favouritesDao.getAll().map {
            it.map { entity ->
                Character(
                    id = entity.id,
                    name = entity.name,
                    status = entity.status,
                    species = entity.species,
                    image = entity.image,
                    origin = entity.origin,
                    episodeDebut = entity.episodeDebut,
                )
            }
        }
    }

    override suspend fun addFavouriteCharacter(character: Character) {
        val entity = FavouriteEntity(
            id = character.id,
            name = character.name,
            status = character.status,
            species = character.species,
            image = character.image,
            origin = character.origin,
            episodeDebut = character.episodeDebut,
        )
        favouritesDao.insert(entity)
    }

    override suspend fun removeFavouriteCharacter(character: Character) {
        favouritesDao.deleteById(character.id)
    }
}
