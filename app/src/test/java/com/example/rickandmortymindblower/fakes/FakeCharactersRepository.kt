package com.example.rickandmortymindblower.fakes

import com.example.rickandmortymindblower.data.repo.CharactersRepository
import com.example.rickandmortymindblower.entity.Character
import com.example.rickandmortymindblower.testbuilder.CharacterBuilder.aCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach

class FakeCharactersRepository : CharactersRepository {

    private var favouriteCharacters = emptyList<Character>()
    private var addFavouriteCharacterCalledWith: Character? = null
    private var removeFavouriteCharacterCalledWith: Character? = null

    @BeforeEach
    fun setup() {
        favouriteCharacters = emptyList()
        addFavouriteCharacterCalledWith = null
        removeFavouriteCharacterCalledWith = null
    }

    override suspend fun getCharacters(): List<Character> {
        return listOf(aCharacter())
    }

    override suspend fun getCharacterById(characterId: String): Character {
        return aCharacter()
    }

    fun givenFavouriteCharacters(returnsTrue: Boolean) {
        favouriteCharacters = if (returnsTrue) listOf(aCharacter()) else emptyList()
    }

    override fun observeFavouriteCharacters(): Flow<List<Character>> {
        return flowOf(favouriteCharacters)
    }

    override suspend fun addFavouriteCharacter(character: Character) {
        addFavouriteCharacterCalledWith = character
    }

    fun verifyAddFavouriteCharacter(character: Character) {
        assertEquals(addFavouriteCharacterCalledWith, character)
    }

    override suspend fun removeFavouriteCharacter(character: Character) {
        removeFavouriteCharacterCalledWith = character
    }

    fun verifyRemoveFavouriteCharacter(character: Character) {
        assertEquals(removeFavouriteCharacterCalledWith, character)
    }
}