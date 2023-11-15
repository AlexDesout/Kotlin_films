package com.example.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun Profil(onClick: () -> Unit, classes : WindowSizeClass) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val classeHauteur = classes.heightSizeClass
    val classeLargeur = classes.widthSizeClass
    when (classeLargeur) {
        WindowWidthSizeClass.Compact -> {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()) {
                Top()
                Presentation()
                Social()
                Button(onClick = { onClick()}) {
                    Text(text = "Commencer")
                }
            }
        } else -> {
        Row (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()) {
            Column (
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(screenWidth /  2)
            )
                {
                Top()
                Presentation()
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(screenWidth /  2)
            ) {
                Social()
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = { onClick()}) {
                    Text(text = "Commencer")
                }
            }
        }

        }
        }
    }


@Composable
fun Top() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()) {
        Image(
            painterResource(id = R.drawable.moi),
            contentDescription = "Moi",
            modifier = Modifier
                .height(150.dp)
                .border(2.dp, Color.Black, CircleShape)
                .clip(CircleShape)
            )
        CircleShape
        Text("Alexandre Desoutter", fontSize = 20.sp, fontWeight = FontWeight.Bold)

    }
}
@Composable
fun Presentation() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Étudiant 3A de BUT MMI")
        Text("IUT Paul Sabatier de Castres")

    }
}
@Composable
fun Social() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Reseau()
            Spacer(modifier = Modifier.width(5.dp))
            Text("alexandre.desoutter@etu.iut-tlse3.fr", textAlign = TextAlign.Start)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Reseau()
            Spacer(modifier = Modifier.width(5.dp))
            Text("www.youtube.com", textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun Reseau(){
    Icon(imageVector = Icons.Rounded.Email, contentDescription = "yoyoyo")
}