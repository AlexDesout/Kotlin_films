package com.example.navigation.ViewModel

import com.example.navigation.Model.TmdbMovie
import com.example.navigation.Model.TmdbMovieResult
import com.example.navigation.Model.TmdbSerieResult
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("trending/movie/week")
    suspend fun popularmovies(@Query("api_key") api_key: String):TmdbMovieResult
    @GET("trending/tv/week")
    suspend fun popularseries(@Query("api_key") api_key: String, @Query("language") language: String):TmdbSerieResult
}