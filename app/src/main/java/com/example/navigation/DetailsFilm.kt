package com.example.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.navigation.Model.Cast
import com.example.navigation.Model.TmdbMovie

@Composable
fun DetailsFilms(viewModel : MainViewModel, filmId : String? ,onClick: () -> Unit) {

    LaunchedEffect(key1 = 0) {
        if (filmId != null) {
            viewModel.getDetailsMovie(filmId)
        }
    }
    val movie by viewModel.detailsMovie.collectAsState()
    Log.i("Credits", movie?.credits?.cast.toString())

    LazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(2),
    )
    {
        movie?.let {
            item(span = { GridItemSpan(2) }) { TopMovie(movie = it) }
            item(span = { GridItemSpan(1) }) { Poster(movie = it) }
            item(span = { GridItemSpan(1) }) { Genres(movie = it) }
            item(span = { GridItemSpan(2) }) { Synopsis(movie = it) }
            item(span = { GridItemSpan(2) }) { Text(text = "Acteurs :", fontWeight = FontWeight.Bold, fontSize = 20.sp) }
            items(it.credits.cast) { movie ->
                Actor(person = movie)
            }
        }


    }
}
@Composable
fun TopMovie(movie: TmdbMovie){
    Column( horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "${movie?.title}", fontWeight = FontWeight.Bold, fontSize = 30.sp)
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w780/${movie?.backdrop_path}",
            contentDescription = movie?.title
        )
    }
}

@Composable
fun Synopsis(movie: TmdbMovie){
    Column() {
        Text(text = "Synopsis :", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        movie?.let { Text(text = it.overview, textAlign = TextAlign.Center) }
    }
}

@Composable
fun Genres(movie: TmdbMovie){
    Column() {
        Text(text = "Genres :", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Row {
            movie.genres.forEachIndexed { index, genre ->
                Text(text = genre.name, fontStyle = FontStyle.Italic)
                if (index < movie.genres.size - 1) {
                    Text(text = ", ")
                }
            }
        }

    }
}
@Composable
fun Poster(movie: TmdbMovie){
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w342/${movie.poster_path}",
        contentDescription = movie.title
    )
}

@Composable
fun Actor(person: Cast) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 100.dp, height = 240.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w342/${person.profile_path}",
                contentDescription = person.name
            )
            Text(
                text = person.name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = person.character,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

