package com.example.navigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.navigation.ViewModel.MainViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import coil.compose.AsyncImage
import com.example.navigation.Model.TmdbMovie

@Composable
fun Films(viewModel : MainViewModel, onClick: () -> Unit) {

    LaunchedEffect(key1 = 0){
        viewModel.getMovies()
    }
    val movies by viewModel.movies.collectAsState()
    Log.i("Films", movies.size.toString())
    LazyColumn () {
        items(movies) { movie ->
            Movie(movie)
        }
        /*
        Text("Premier écran")
        Button(onClick = onClick) {
            Text("écran")

         */
        }
    }


@Composable
fun Movie(movie: TmdbMovie) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500/${movie.backdrop_path}",
        contentDescription = "Ma super image"
    )
    Text(text = movie.original_title)
}