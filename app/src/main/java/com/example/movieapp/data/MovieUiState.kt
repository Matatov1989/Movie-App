package com.example.movieapp.data

import com.example.movieapp.model.Movie
import java.lang.Exception

sealed class MovieUiState {
    data class Success(val movies: List<Movie>) : MovieUiState()
    data class Error(val exception: Exception) : MovieUiState()
    data class Loading(val isLoad: Boolean) : MovieUiState()
}