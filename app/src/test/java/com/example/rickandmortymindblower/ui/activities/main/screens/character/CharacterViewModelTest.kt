@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.rickandmortymindblower.ui.activities.main.screens.character

import androidx.lifecycle.SavedStateHandle
import com.example.rickandmortymindblower.entity.Character
import com.example.rickandmortymindblower.fakes.FakeCharactersRepository
import com.example.rickandmortymindblower.testbuilder.CharacterBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharacterViewModelTest {

    private val savedStateHandle = SavedStateHandle()
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
    fun `when init, then fetch character details`() {
        savedStateHandle["characterId"] = "id"
        val expectedCharacter = Character(
            id = "1",
            name = "Rick",
            status = "Alive",
            species = "Human",
            image = "ImageUrl",
            origin = "C-137",
            episodeDebut = "1",
        )

        val viewModel = CharacterViewModel(savedStateHandle, charactersRepository)

        assertEquals(expectedCharacter, viewModel.character)
    }

    @Test
    fun `given character is not favourite, when toggleFavourite, then addFavouriteCharacter`() {
        val character = CharacterBuilder.aCharacter(isFavourite = false)

        val viewModel = CharacterViewModel(savedStateHandle, charactersRepository)
        viewModel.toggleFavourite(character)

        charactersRepository.verifyAddFavouriteCharacter(character)
    }

    @Test
    fun `given character is favourite, when toggleFavourite, then removeFavouriteCharacter`() {
        val character = CharacterBuilder.aCharacter(isFavourite = true)

        val viewModel = CharacterViewModel(savedStateHandle, charactersRepository)
        viewModel.toggleFavourite(character)

        charactersRepository.verifyRemoveFavouriteCharacter(character)
    }
}
