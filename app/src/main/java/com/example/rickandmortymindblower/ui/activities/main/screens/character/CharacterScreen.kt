@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortymindblower.ui.activities.main.screens.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
            FavouriteButton(
                modifier = Modifier.padding(start = 8.dp, top = 24.dp),
                viewModel = viewModel,
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
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

@Composable
private fun FavouriteButton(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel,
) {
    val character = viewModel.character ?: return

    IconButton(
        modifier = modifier,
        onClick = { viewModel.toggleFavourite(character) },
    ) {
        val icon = if (character.isFavourite) Icons.Filled.Star else Icons.Outlined.StarOutline
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = icon,
            contentDescription = null,
        )
    }
}
