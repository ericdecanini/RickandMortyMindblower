@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortymindblower.ui.activities.main.screens.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val character = viewModel.character

    if (character != null) {
        Column {
            TopAppBar(title = { Text(text = character.name) })
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = character.image,
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                text = "Species: ${character.species}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                text = "Status: ${character.status}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                text = "Origin: ${character.origin}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                text = "Episode Debut: ${character.episodeDebut}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
