package com.example.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            Log.i("Recherche", currentDestination?.route?: "Non trouvé")

            val destinations = listOf(Destination.Film)
            Scaffold(
                bottomBar = { if (currentDestination?.route != "profil") {
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
                }}) { innerPadding ->
                NavHost(
                    navController, startDestination = Destination.Profil.destination,
                    Modifier.padding(innerPadding)
                ) {
                    composable(Destination.Profil.destination) { Profil({ navController.navigate("film") }) }
                    composable(Destination.Film.destination) { Films({ navController.navigate("profil") }) }
                }
            }
        }
    }
    }
