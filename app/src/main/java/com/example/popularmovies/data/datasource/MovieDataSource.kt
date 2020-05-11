package com.example.popularmovies.data.datasource

import androidx.lifecycle.LiveData
import com.example.popularmovies.data.db.model.Movie
import kotlinx.coroutines.Deferred

interface MovieDataSource {

    suspend fun loadTopRatedMovies(): Deferred<List<Movie>>

    suspend fun loadMostPopularMovies() : Deferred<List<Movie>>

    suspend fun loadMovieDetails()

}