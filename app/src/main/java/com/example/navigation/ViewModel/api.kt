package com.example.navigation.ViewModel

import com.example.navigation.Model.Credits
import com.example.navigation.Model.TmdbActor
import com.example.navigation.Model.TmdbActorResult
import com.example.navigation.Model.TmdbMovie
import com.example.navigation.Model.TmdbMovieResult
import com.example.navigation.Model.TmdbSerieResult
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface Api {
    @GET("trending/movie/week")
    suspend fun popularmovies(@Query("api_key") api_key: String):TmdbMovieResult
    @GET("search/movie")
    suspend fun searchmovies(@Query("api_key") api_key: String, @Query("query") filmQuery: String, @Query("language") language: String):TmdbMovieResult
    @GET("trending/tv/week")
    suspend fun popularseries(@Query("api_key") api_key: String, @Query("language") language: String):TmdbSerieResult
    @GET("trending/person/week")
    suspend fun popularactors(@Query("api_key") api_key: String, @Query("language") language: String):TmdbActorResult

    @GET("movie/{idFilm}")
    suspend fun detailsMovie(@Path("idFilm") idFilm : String, @Query("api_key") api_key: String, @Query("language") language: String, @Query("append_to_response") credits: String):TmdbMovie

    @GET("person/{idFilm}")
    suspend fun detailsActor(@Path("idFilm") idFilm : String, @Query("api_key") api_key: String, @Query("language") language: String, @Query("append_to_response") credits: String):TmdbActor
}