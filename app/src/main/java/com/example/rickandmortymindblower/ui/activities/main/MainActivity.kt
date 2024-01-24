package com.example.rickandmortymindblower.ui.activities.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                        startDestination = "character_list_screen"
                    ) {
                        composable("character_list_screen") {
                            HomeScreen(navController = navController)
                        }
                        composable("character_detail_screen/{characterName}",
                            arguments = listOf(
                                navArgument("characterName") {
                                    type = NavType.StringType
                                }
                            )
                        )
                        {
                            val characterName = remember {
                                it.arguments?.getString("characterName")
                            }
                        }
                    }
                }
            }
        }
    }
}
