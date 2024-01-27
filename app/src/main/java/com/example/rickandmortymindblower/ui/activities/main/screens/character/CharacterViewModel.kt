package com.example.rickandmortymindblower.ui.activities.main.screens.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortymindblower.data.repo.CharactersRepository
import com.example.rickandmortymindblower.entity.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val charactersRepository: CharactersRepository,
): ViewModel() {

    var character by mutableStateOf<Character?>(null)
        private set

    init {
        getCharacterDetails()
    }

    private fun getCharacterDetails() {
        val characterId: String? = savedStateHandle["characterId"]
        if (characterId != null) {
            viewModelScope.launch {
                character = charactersRepository.getCharacterById(characterId)
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
        this.character = character.copy(isFavourite = !character.isFavourite)
    }
}
