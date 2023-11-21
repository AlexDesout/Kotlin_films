package com.example.navigation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.navigation.Model.CastActor
import com.example.navigation.Model.TmdbActor
import com.example.navigation.ViewModel.MainModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun DetailsActors(viewModel: MainModel, actorId: String?, onClick: (filmId : String) -> Unit) {

    LaunchedEffect(key1 = 0) {
        if (actorId != null) {
            viewModel.getDetailsActor(actorId)
        }
    }

    val actor by viewModel.detailsActor.collectAsState()
    Log.i("ACTEUR", actor?.credits.toString())

    //details?.let { Text(text = it.name) }
    LazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(2),
    )
    {
        actor?.let {
            item(span = { GridItemSpan(2) }) { TopActor(actor = it) }
            item(span = { GridItemSpan(2) }) { Informations(actor = it) }
            item(span = { GridItemSpan(2) }) { Biography(actor = it) }
            item(span = { GridItemSpan(2) }) { Text(text = "Réalisations :", fontWeight = FontWeight.Bold, fontSize = 20.sp) }
            items(it.credits.cast) { cast ->
                Movie(cast = cast, onClick)
            }
        }
    }
}

@Composable
fun TopActor(actor: TmdbActor) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${actor.profile_path}",
            contentDescription = actor.original_name
        )
        Text(
            text = actor.name,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}

@Composable
fun Informations(actor: TmdbActor) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fonction principale : ${actor.known_for_department}",
        )
        Text(
            text = "Popularité : ${actor.popularity} / 100"
        )
    }
}
@Composable
fun Biography(actor: TmdbActor) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = actor.biography,
        )
    }
}
@Composable
fun Movie(cast: CastActor, onClick: (filmId: String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick(cast.id.toString()) }
    ) {
        Card (
            modifier = Modifier
                .size(width = 150.dp, height = 260.dp)
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage (
                    model = "https://image.tmdb.org/t/p/w500/${cast.poster_path}",
                    contentDescription = cast.original_title
                )
                Text(text = cast.original_title)
            }
        }
    }
}