package com.example.movieapp.network

import com.example.movieapp.model.MovieListResponse
import com.example.movieapp.util.Constants.MOVIE_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = MOVIE_API_KEY
    ): Response<MovieListResponse>

    @GET("movie/now_playing")
    suspend fun getPlayingNowMovies(
        @Query("api_key") apiKey: String = MOVIE_API_KEY
    ): Response<MovieListResponse>
}