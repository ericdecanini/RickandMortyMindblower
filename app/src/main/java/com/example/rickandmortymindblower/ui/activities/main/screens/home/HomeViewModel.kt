package com.example.rickandmortymindblower.ui.activities.main.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortymindblower.data.repo.CharactersRepository
import com.example.rickandmortymindblower.entity.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
) : ViewModel() {

    var characters by mutableStateOf(emptyList<Character>())
        private set

    init {
        loadPage()
    }

    private fun loadPage() {
        viewModelScope.launch {
            fetchCharactersList()
            observeFavourites()
        }
    }

    private suspend fun fetchCharactersList() {
        val charactersResult = charactersRepository.getCharacters()
        characters = charactersResult
    }

    private suspend fun observeFavourites() {
        charactersRepository.observeFavouriteCharacters().collectLatest { favourites ->
            val favouriteIds = favourites.map { it.id }
            characters = characters.map {
                it.copy(isFavourite = it.id in favouriteIds)
            }
        }
    }

    fun toggleFavourite(character: Character) {
        viewModelScope.launch {
            if (character.isFavourite) {
                charactersRepository.removeFavouriteCharacter(character)
            } else {
                charactersRepository.addFavouriteCharacter(character)
            }
        }
    }
}
