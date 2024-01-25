package com.example.rickandmortymindblower.fakes

import com.example.rickandmortymindblower.data.repo.CharactersRepository
import com.example.rickandmortymindblower.entity.Character
import com.example.rickandmortymindblower.testbuilder.CharacterBuilder.aCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCharactersRepository : CharactersRepository {

    override suspend fun getCharacters(): List<Character> {
        return listOf(aCharacter())
    }

    override suspend fun getCharacterById(characterId: String): Character {
        return aCharacter()
    }

    override fun observeFavouriteCharacters(): Flow<List<Character>> {
        return flowOf(listOf(aCharacter()))
    }

    override suspend fun addFavouriteCharacter(character: Character) {

    }

    override suspend fun removeFavouriteCharacter(character: Character) {

    }
}