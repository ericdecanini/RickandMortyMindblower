package com.example.rickandmortymindblower.ui.activities.main.screens.character

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CharacterDetailScreen(character: String) {
    Text(text = "Character name: $character")
}