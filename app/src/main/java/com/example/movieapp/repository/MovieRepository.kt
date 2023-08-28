package com.example.movieapp.repository

import com.example.movieapp.enums.MovieType
import com.example.movieapp.model.MovieListResponse
import com.example.movieapp.network.MovieApi
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: MovieApi) {

    suspend fun getPopularMovies(typeMovie: MovieType): Response<MovieListResponse> {
        return if (typeMovie == MovieType.Popular) api.getPopularMovies()
        else api.getPlayingNowMovies()
    }
}