package com.example.movieapp.fragments.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieUiState
import com.example.movieapp.enums.MovieType
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val movieUiState = MutableStateFlow<MovieUiState>(MovieUiState.Success(emptyList()))
    val movieLiveData: StateFlow<MovieUiState> = movieUiState

    fun getMovies(typeMovie: MovieType) {
        viewModelScope.launch {
            delay(1000L)
            try {
                movieUiState.value = MovieUiState.Loading(true)
                if (typeMovie == MovieType.Favorite) {
                    movieUiState.value = MovieUiState.Success(repository.getFavoriteMovie())
                } else {
                    val response = repository.getMovies(typeMovie)
                    val list = response.body()?.results
                    list?.let {
                        movieUiState.value = MovieUiState.Success(it)
                    }
                }
            } catch (e: Exception) {
                Log.e("RESULT_EXCEPTION", "result: $e")
                movieUiState.value = MovieUiState.Error(e)
            } finally {
                movieUiState.value = MovieUiState.Loading(false)
            }
        }
    }
}