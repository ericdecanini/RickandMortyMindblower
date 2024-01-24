package com.example.rickandmortymindblower.ui.activities.main.screens.character

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterViewModel = hiltViewModel()
) {
    Text(text = "Character name: ${viewModel.character?.name}")
}
