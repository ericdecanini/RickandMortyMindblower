@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortymindblower.ui.activities.main.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.rickandmortymindblower.ui.activities.main.MainViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    Column {
        TopAppBar(title = { Text("Rick and Morty Mindblower") })
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            viewModel.characters.forEach {
                item { CharacterBox(it) }
            }
        }
    }
}

@Composable
fun CharacterBox(character: Character) {
    OutlinedCard(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            CharacterImage(character)
            CharacterInfo(character)
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
//    val textColor = MaterialTheme.colors.onPrimary
    Column(
        modifier = Modifier.padding(start = 20.dp),
    ) {
        Text(
            text = "Name: ${character.name}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
//            color = textColor,
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "Status: ${character.status}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
//            color = textColor,
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "Species: ${character.species}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
//            color = textColor,
        )
    }
}
