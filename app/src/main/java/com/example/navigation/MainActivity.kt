package com.example.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ViewModel.MainModel


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainModel by viewModels()
        setContent {

            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            Log.i("Recherche", currentDestination?.route ?: "Non trouvé")
            var isSearchVisible by remember { mutableStateOf(false) }
            var searchQuery by remember { mutableStateOf("") }

            val destinations = listOf(Destination.Film, Destination.Series, Destination.Actors)
            Scaffold(
                topBar = {
                    if (currentDestination?.route != "profil") {
                        TopAppBar(
                            title = { Text("FilmoTron-3000") },
                            actions = {
                                if (isSearchVisible && currentDestination?.route == "film") {
                                    Row(
                                        modifier =
                                        Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        OutlinedTextField(
                                            modifier = Modifier.background(Color.Transparent),
                                            value = searchQuery,
                                            onValueChange = {
                                                searchQuery = it
                                            },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Text,
                                            ),
                                        )
                                        IconButton(onClick = {
                                            isSearchVisible = false
                                            searchQuery = ""
                                        }) {
                                            Icon(
                                                Icons.Filled.Close,
                                                contentDescription = "Localized description"
                                            )
                                        }
                                    }
                                } else {
                                    IconButton(onClick = { isSearchVisible = true }) {
                                        Icon(
                                            Icons.Filled.Search,
                                            contentDescription = "Localized description"
                                        )
                                    }
                                }

                                IconButton(onClick = { }) {
                                    Icon(
                                        Icons.Filled.Favorite,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        )
                    }
                },

                bottomBar = {
                    if (currentDestination?.route != "profil") {
                        BottomNavigation {
                            destinations.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.icon, contentDescription = null) },
                                    label = { Text(screen.label) },
                                    selected =
                                    currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
                                    onClick = { navController.navigate(screen.destination) })
                            }
                        }
                    }
                }) { innerPadding ->
                NavHost(
                    navController, startDestination = Destination.Profil.destination,
                    Modifier.padding(innerPadding)
                ) {
                    // Route profil
                    composable(Destination.Profil.destination) {
                        Profil(
                            { navController.navigate("film") },
                            windowSizeClass
                        )
                    }
                    // Route liste films
                    composable(Destination.Film.destination) {
                        Films(viewModel, searchQuery) { filmId ->
                            navController.navigate("detailsFilm/$filmId")
                        }
                    }
                    // Route liste series
                    composable(Destination.Series.destination) {
                        Series(viewModel) {
                            serieId-> navController.navigate(
                                "detailsSerie/$serieId"
                            )
                        }
                    }
                    // Route liste acteurs
                    composable(Destination.Actors.destination) {
                        Actors(viewModel) {
                                actorId ->
                            navController.navigate("detailsActor/$actorId")
                        }
                    }
                    // Route details film
                    composable("detailsFilm/{filmId}") { backStackEntry ->
                        val filmId = backStackEntry.arguments?.getString("filmId")
                        DetailsFilms(viewModel, filmId) {actorId ->
                            navController.navigate("detailsActor/$actorId")
                        }
                    }
                    // Route details acteur
                    composable("detailsActor/{actorId}") { backStackEntry ->
                        val actorId = backStackEntry.arguments?.getString("actorId")
                        DetailsActors(viewModel, actorId) {filmId ->
                            navController.navigate("detailsFilm/$filmId")
                        }
                    }
                    // Route details series
                    composable("detailsSerie/{serieId}") { backStackEntry ->
                        val serieId = backStackEntry.arguments?.getString("serieId")
                        DetailsSerie(viewModel, serieId) {actorId ->
                            navController.navigate("detailsActor/$actorId")
                        }
                    }
                }
            }
        }
    }
}
