package com.example.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.navigation.ViewModel.MainViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.navigation.Model.TmdbMovie

@Composable
fun Films(viewModel : MainViewModel, onClick: () -> Unit) {

    LaunchedEffect(key1 = 0){
        viewModel.getMovies()
    }
    val movies by viewModel.movies.collectAsState()
    Log.i("Films", movies.size.toString())
    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
    ) {
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
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w400/${movie.backdrop_path}",
            contentDescription = "Ma super image"
        )
        Text(text = movie.original_title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(text = movie.release_date)
    }

}