package com.example.navigation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.Model.TmdbActor
import com.example.navigation.Model.TmdbMovie
import com.example.navigation.Model.TmdbSerie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainModel : ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Api::class.java)
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val detailsMovie = MutableStateFlow<TmdbMovie?>(null)
    val detailsActor = MutableStateFlow<TmdbActor?>(null)
    val series = MutableStateFlow<List<TmdbSerie>>(listOf())
    val actors = MutableStateFlow<List<TmdbActor>>(listOf())
    val api_key: String = "1aafb842f039fc0eea914e8e3da318ce"
    val credits: String = "credits"
    val language: String = "fr"

    fun getMovies() {
        viewModelScope.launch {
            movies.value = api.popularmovies(api_key).results
        }
    }

    fun searchMovies(filmQuery: String) {
        viewModelScope.launch {
            movies.value = api.searchmovies(api_key, filmQuery, language).results
        }
    }

    fun getDetailsMovie(idFilm: String) {
        viewModelScope.launch {
            detailsMovie.value = api.detailsMovie(idFilm, api_key, language, credits)
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            series.value = api.popularseries(api_key, language).results
        }
    }

    fun getActors() {
        viewModelScope.launch {
            actors.value = api.popularactors(api_key, language).results
        }
    }

    fun getDetailsActor(idActor: String) {
        viewModelScope.launch {
            detailsActor.value = api.detailsActor(idActor, api_key, language, credits)
        }
    }
}