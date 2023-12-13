package com.example.navigation

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.navigation.Model.TmdbSerie
import com.example.navigation.ViewModel.MainModel

@Composable
fun Series(viewModel: MainModel, onClick: (serieId: String) -> Unit) {

    LaunchedEffect(key1 = 0){
        viewModel.getSeries()
    }
    val series by viewModel.series.collectAsState()
    Log.i("Series", series.size.toString())

    LazyVerticalGrid (
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
        items(series) { serie ->
            Serie(serie, onClick)
        }
        /*
        Text("Premier écran")
        Button(onClick = onClick) {
            Text("écran")

         */
    }

}


@Composable
fun Serie(serie: TmdbSerie, onClick: (serieId: String) -> Unit) {
    ElevatedCard (elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp
    ),
        modifier = Modifier
            .size(width = 100.dp, height = 240.dp)
            .clickable { onClick(serie.id.toString()) }
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w342/${serie.poster_path}",
                contentDescription = serie.name
            )
            Text(text = serie.name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(text = serie.first_air_date)
        }
    }

}