package com.example.popularmovies.data.repository

import androidx.lifecycle.LiveData
import com.example.popularmovies.data.db.model.Movie

interface MovieRepository {
    suspend fun getTopRatedMovies(): LiveData<List<Movie>>

    suspend fun getMostPopular(): LiveData<List<Movie>>
}