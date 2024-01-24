@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.rickandmortymindblower.ui.activities.main.screens.character

import androidx.lifecycle.SavedStateHandle
import com.example.rickandmortymindblower.entity.Character
import com.example.rickandmortymindblower.fakes.FakeCharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
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
}
