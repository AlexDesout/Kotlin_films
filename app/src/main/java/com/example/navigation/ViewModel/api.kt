package com.example.navigation.ViewModel

import com.example.navigation.Model.TmdbMovie
import com.example.navigation.Model.TmdbMovieResult
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("trending/movie/week")
    suspend fun popularmovies(@Query("api_key") api_key: String):TmdbMovieResult
}