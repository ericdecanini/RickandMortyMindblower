@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortymindblower.ui.activities.main.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmortymindblower.entity.Character


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Column {
        TopAppBar(title = { Text("Rick and Morty Mindblower") })
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            viewModel.characters.forEach {
                item { CharacterBox(it, navController, viewModel) }
            }
        }
    }
}

@Composable
fun CharacterBox(
    character: Character,
    navController: NavController,
    viewModel: HomeViewModel,
) {
    OutlinedCard(
        modifier = Modifier
            .padding(8.dp)
            .clickable { navController.navigate("character/${character.id}") }) {
        Box {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                CharacterImage(character)
                CharacterInfo(character)
            }
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = { viewModel.toggleFavourite(character) },
            ) {
                val icon = if (character.isFavourite) Icons.Filled.Star else Icons.Outlined.StarOutline
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    }
}

@Composable
private fun CharacterImage(character: Character) {
    AsyncImage(
        model = character.image,
        contentDescription = null
    )
}

@Composable
private fun CharacterInfo(character: Character) {
    Column(
        modifier = Modifier.padding(start = 20.dp),
    ) {
        Text(
            text = "Name: ${character.name}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "Status: ${character.status}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "Species: ${character.species}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
