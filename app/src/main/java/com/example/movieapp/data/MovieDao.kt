package com.example.movieapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: Movie)

    @Query("SELECT * FROM MovieTable")
    suspend fun getFavoriteMovie(): List<Movie>
}