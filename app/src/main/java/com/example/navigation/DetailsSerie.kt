package com.example.navigation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.navigation.Model.TmdbSerie
import com.example.navigation.ViewModel.MainModel

@Composable
fun DetailsSerie(viewModel: MainModel, serieId : String?, onClick: (actorId: String) -> Unit) {


    LaunchedEffect(key1 = 0) {
        if (serieId != null) {
            viewModel.getDetailsSerie(serieId)
        }
    }
    val serie by viewModel.detailsSerie.collectAsState()
    Log.i("Credits", serie?.credits?.cast.toString())

    LazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(2),
    )
    {
        serie?.let {
            item(span = { GridItemSpan(2) }) { TopSerie(serie = it) }
            item(span = { GridItemSpan(1) }) { Poster(serie = it) }
            item(span = { GridItemSpan(1) }) { Genres(serie = it) }
            item(span = { GridItemSpan(2) }) { Synopsis(serie = it) }
            item(span = { GridItemSpan(2) }) { Text(text = "Acteurs :", fontWeight = FontWeight.Bold, fontSize = 20.sp) }
            items(it.credits.cast) { cast ->
                ActorSerie(person = cast, onClick)
            }
        }





    }
}

@Composable
fun TopSerie(serie: TmdbSerie){
    Column( horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "${serie?.name}", fontWeight = FontWeight.Bold, fontSize = 30.sp)
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w780/${serie?.backdrop_path}",
            contentDescription = serie?.name
        )
    }
}



@Composable
fun Synopsis(serie: TmdbSerie){
    Column() {
        Text(text = "Synopsis :", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        serie?.let { Text(text = it.overview, textAlign = TextAlign.Center) }
    }
}
@Composable
fun Genres(serie: TmdbSerie) {
    Column() {
        Text(text = "Genres :", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(serie.genres) { genre ->
                Text(text = genre.name, fontStyle = FontStyle.Italic)
            }
        }
    }
}

@Composable
fun Poster(serie: TmdbSerie){
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w342/${serie.poster_path}",
        contentDescription = serie.name
    )
}


@Composable
fun ActorSerie(person: Cast, onClick: (serieId: String) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 100.dp, height = 240.dp)
            .clickable { onClick(person.id.toString()) }


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