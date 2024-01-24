package com.example.rickandmortymindblower.data.repo

import com.example.rickandmortymindblower.entity.Character
import com.example.rickandmortymindblower.fakes.FakeRickAndMortyApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CharactersRepositoryTest {

    private val rickAndMortyApi = FakeRickAndMortyApi()
    private val charactersRepository = CharactersRepository(rickAndMortyApi)

    @Test
    fun `when getCharacters, then get from api and map`() = runTest {
        val expectedCharacters = listOf(Character(
            id = "1",
            name = "Rick",
            status = "Alive",
            species = "Human",
            image = "ImageUrl",
            origin = "C-137",
            episodeDebut = "1",
        ))

        val characters = charactersRepository.getCharacters()

        assertEquals(expectedCharacters, characters)
    }

    @Test
    fun `when getCharacterById, then get from api and map`() = runTest {
        val expectedCharacter = Character(
            id = "1",
            name = "Rick",
            status = "Alive",
            species = "Human",
            image = "ImageUrl",
            origin = "C-137",
            episodeDebut = "1",
        )

        val character = charactersRepository.getCharacterById("id")

        assertEquals(expectedCharacter, character)
    }
}