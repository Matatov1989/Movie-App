package com.example.movieapp.repository

import com.example.movieapp.data.MovieDao
import com.example.movieapp.enums.MovieType
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieListResponse
import com.example.movieapp.network.MovieApi
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieApi,
    private val movieDao: MovieDao
) {

    suspend fun getMovies(typeMovie: MovieType): Response<MovieListResponse> {
        return if (typeMovie == MovieType.Popular) api.getPopularMovies()
        else api.getPlayingNowMovies()
    }

    suspend fun insertFavorite(movie: Movie) = movieDao.insertFavorite(movie)

    suspend fun getFavoriteMovie() = movieDao.getFavoriteMovie()
}