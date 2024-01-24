package com.example.rickandmortymindblower.ui.activities.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortymindblower.ui.activities.main.screens.character.CharacterDetailScreen
import com.example.rickandmortymindblower.ui.activities.main.screens.home.HomeScreen
import com.example.rickandmortymindblower.ui.theme.RickAndMortyMindblowerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyMindblowerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(navController = navController)
                        }
                        composable(
                            route = "character/{characterId}",
                            arguments = listOf(
                                navArgument("characterId") {
                                    type = NavType.StringType
                                }
                            ),
                        ) {
                            CharacterDetailScreen()
                        }
                    }
                }
            }
        }
    }
}
