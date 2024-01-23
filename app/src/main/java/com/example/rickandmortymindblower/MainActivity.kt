package com.example.rickandmortymindblower

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.rickandmortymindblower.characterlist.CharacterListScreen
import com.example.rickandmortymindblower.repo.MainRepository
import com.example.rickandmortymindblower.ui.theme.RickandMortyMindblowerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.Response

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandMortyMindblowerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "character_list_screen"
                ) {
                    composable("character_list_screen") {
                        CharacterListScreen(navController = navController)
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
