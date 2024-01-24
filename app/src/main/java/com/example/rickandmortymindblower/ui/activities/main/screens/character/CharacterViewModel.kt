package com.example.rickandmortymindblower.ui.activities.main.screens.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
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
    savedStateHandle: SavedStateHandle,
    private val charactersRepository: CharactersRepository,
): ViewModel() {

    var character by mutableStateOf<Character?>(null)
        private set

    init {
        val characterId: String? = savedStateHandle["characterId"]
        characterId?.let {
            viewModelScope.launch {
                character = charactersRepository.getCharacterById(characterId)
            }
        }
    }
}
