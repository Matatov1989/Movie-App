package com.example.movieapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.model.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}