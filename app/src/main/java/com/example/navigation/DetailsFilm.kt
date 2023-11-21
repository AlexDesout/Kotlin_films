package com.example.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.navigation.ViewModel.MainViewModel
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.navigation.Model.TmdbActor
import com.example.navigation.Model.TmdbMovie
import com.example.navigation.Model.TmdbSerie

@Composable
fun DetailsFilms(viewModel : MainViewModel, filmId : String? ,onClick: () -> Unit) {

    LaunchedEffect(key1 = 0){
        if (filmId != null) {
            viewModel.getDetailsMovie(filmId)
        }
    }
    val movie by viewModel.detailsMovie.collectAsState()

    Column (
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        movie?.let { TopMovie(it) }
        movie?.let { Genres(movie = it) }
        movie?.let { Synopsis(movie = it) }
        movie?.let { Cast(movie = it) }


    }
}

@Composable
fun TopMovie(movie: TmdbMovie){
    Text(text = "${movie?.title}", fontWeight = FontWeight.Bold, fontSize = 30.sp)
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w780/${movie?.backdrop_path}",
        contentDescription = movie?.title
    )
}

@Composable
fun Synopsis(movie: TmdbMovie){
    Text(text = "Synopsis :", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    movie?.let { Text(text = it.overview) }
}

@Composable
fun Genres(movie: TmdbMovie){
    Text(text = "Genres :", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    movie?.genres?.forEach { genre ->
        Text(text = genre.name, fontStyle = FontStyle.Italic)
    }
}
@Composable
fun Cast(movie: TmdbMovie){
    movie?.credits?.cast?.get(0)?.let { Text(text = it.name) }
}
