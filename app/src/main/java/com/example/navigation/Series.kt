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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.navigation.Model.TmdbSerie

@Composable
fun Series(viewModel : MainViewModel, onClick: () -> Unit) {

    LaunchedEffect(key1 = 0){
        viewModel.getSeries()
    }
    val series by viewModel.series.collectAsState()
    Log.i("Series", series.size.toString())

    LazyVerticalGrid (
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(series) { serie ->
            Serie(serie)
        }
        /*
        Text("Premier écran")
        Button(onClick = onClick) {
            Text("écran")

         */
    }

}


@Composable
fun Serie(serie: TmdbSerie) {
    ElevatedCard (elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp
    ),
        modifier = Modifier
            .size(width = 100.dp, height = 200.dp)
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w780/${serie.backdrop_path}",
                contentDescription = serie.name
            )
            Text(text = serie.name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(text = serie.first_air_date)
        }
    }

}