package com.example.navigation.Model

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class TmdbMovieResult(
    var page: Int = 0,
    val results: List<TmdbMovie> = listOf())

class TmdbMovie(
    var overview: String = "",
    val release_date: String = "",
    val id: String = "",
    val title: String = "",
    val original_title: String = "",
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val poster_path: String? = "",
    val genres: List<Genre>,
    val credits: Credits,
)


class TmdbSerieResult(
    var page: Int = 0,
    val results: List<TmdbSerie> = listOf())
data class TmdbSerie(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genres: List<Genre>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,
    val credits: Credits,
)
class TmdbActorResult(
    var page: Int = 0,
    val results: List<TmdbActor> = listOf())
data class TmdbActor(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val media_type: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String,
    val release_date: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val biography: String,
    val credits : CreditsActor
)


/* Les différentes sous classes */
data class Credits(
    val cast: List<Cast>,
)
data class CreditsActor(
    val cast: List<CastActor>,
)

data class Genre(
    val id: Int,
    val name: String
)

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class CastActor(
    val adult: Boolean,
    val backdrop_path: String,
    val character: String,
    val credit_id: String,
    val genre_ids: List<Int>,
    val id: Int,
    val order: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
