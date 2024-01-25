@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.rickandmortymindblower.data.repo

import com.example.rickandmortymindblower.fakes.FakeFavouritesDao
import com.example.rickandmortymindblower.fakes.FakeRickAndMortyApi
import com.example.rickandmortymindblower.testbuilder.CharacterBuilder.aCharacter
import com.example.rickandmortymindblower.testbuilder.FavouriteEntityBuilder.aFavouriteEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CharactersRepositoryImplTest {

    private val rickAndMortyApi = FakeRickAndMortyApi()
    private val favouritesDao = FakeFavouritesDao()
    private val charactersRepository = CharactersRepositoryImpl(
        rickAndMortyApi,
        favouritesDao,
    )

    @Test
    fun `when getCharacters, then get from api and map`() = runTest {
        val expectedCharacters = listOf(aCharacter())

        val characters = charactersRepository.getCharacters()

        assertEquals(expectedCharacters, characters)
    }

    @Test
    fun `when getCharacterById, then get from api and map`() = runTest {
        val expectedCharacter = aCharacter()

        val character = charactersRepository.getCharacterById("id")

        assertEquals(expectedCharacter, character)
    }

    @Test
    fun `when observeFavouriteCharacters, then get flow of favourites from dao`() = runTest {
        val expectedCharacters = listOf(aCharacter())

        val result = charactersRepository.observeFavouriteCharacters().first()

        assertEquals(expectedCharacters, result)
    }

    @Test
    fun `when addFavouriteCharacter, then character is inserted in dao`() = runTest {
        charactersRepository.addFavouriteCharacter(aCharacter())

        favouritesDao.verifyInsert(aFavouriteEntity())
    }

    @Test
    fun `when removeFavouriteCharacter, then character is deleted in dao`() = runTest {
        val character = aCharacter()

        charactersRepository.removeFavouriteCharacter(character)

        favouritesDao.verifyDeleteById(character.id)
    }
}
