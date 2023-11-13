package com.example.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Films(onClick: () -> Unit) {
    Column() {
        Text("Premier écran")
        Button(onClick = onClick) {
            Text("écran")
        }
    }
}