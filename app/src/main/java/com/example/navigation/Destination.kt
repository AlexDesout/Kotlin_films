package com.example.navigation

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Destination(val destination: String, val label: String, val icon: ImageVector) {
    object Profil : Destination("profil", "Mon Profil",Icons.Filled.Edit)
    object Film : Destination("film", "Films", Icons.Filled.Star)
    object Series : Destination("series", "Séries", Icons.Filled.PlayArrow)
    object Actors : Destination("actors", "Acteurs", Icons.Filled.Person)
}