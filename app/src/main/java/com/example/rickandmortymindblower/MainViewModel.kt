package com.example.rickandmortymindblower

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortymindblower.repo.Character
import com.example.rickandmortymindblower.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
    ) : ViewModel() {

    var characters by mutableStateOf(emptyList<Character>())
        private set

    init {
        fetchCharactersList()
    }

        private fun fetchCharactersList() {
            viewModelScope.launch {
                val characters = mainRepository.getCharacters()
                this@MainViewModel.characters = characters
            }
        }
}
