@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.rickandmortymindblower.ui.activities.main.screens.home

import com.example.rickandmortymindblower.fakes.FakeCharactersRepository
import com.example.rickandmortymindblower.testbuilder.CharacterBuilder.aCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeViewModelTest {

    private val charactersRepository = FakeCharactersRepository()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterEach
    fun reset() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when init, then fetch character list`() {
        val expectedCharacters = listOf(aCharacter())

        val viewModel = HomeViewModel(charactersRepository)

        assertEquals(expectedCharacters, viewModel.characters)
    }

    @Test
    fun `given repository returns favourite character, when init, then character is set as favourite`() {
        charactersRepository.givenFavouriteCharacters(returnsTrue = true)

        val viewModel = HomeViewModel(charactersRepository)

        assertTrue(viewModel.characters.first().isFavourite)
    }

    @Test
    fun `given repository does not return favourite character, when init, then character is not set as favourite`() {
        charactersRepository.givenFavouriteCharacters(returnsTrue = false)

        val viewModel = HomeViewModel(charactersRepository)

        assertFalse(viewModel.characters.first().isFavourite)
    }

    @Test
    fun `given character is not favourite, when toggleFavourite, then addFavouriteCharacter`() {
        val character = aCharacter(isFavourite = false)

        val viewModel = HomeViewModel(charactersRepository)
        viewModel.toggleFavourite(character)

        charactersRepository.verifyAddFavouriteCharacter(character)
    }

    @Test
    fun `given character is favourite, when toggleFavourite, then removeFavouriteCharacter`() {
        val character = aCharacter(isFavourite = true)

        val viewModel = HomeViewModel(charactersRepository)
        viewModel.toggleFavourite(character)

        charactersRepository.verifyRemoveFavouriteCharacter(character)
    }
}
