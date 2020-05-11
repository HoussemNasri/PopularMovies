package com.example.popularmovies.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.popularmovies.data.datasource.remote.RemoteMovieDataSource
import com.example.popularmovies.data.db.MoviesDao
import com.example.popularmovies.data.db.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("UNUSED_VARIABLE")
class MovieRepositoryImpl(
    private val moviesDao: MoviesDao,
    private val remoteMovieDataSource: RemoteMovieDataSource
) : MovieRepository {
    private val TAG = "__MovieRepository"

    override suspend fun getTopRatedMovies(): LiveData<List<Movie>> {
        return withContext(Dispatchers.IO) {
            initTopRated()
            return@withContext moviesDao.getTopRatedMovies()
        }
    }

    override suspend fun getMostPopular(): LiveData<List<Movie>> {
        return withContext(Dispatchers.IO) {
            initMostPopular()
            return@withContext moviesDao.getMostPopularMovies()
        }
    }

    private suspend fun initMostPopular() {
        if (true) {
            val mostPopularList = remoteMovieDataSource.loadMostPopularMovies().await()
            moviesDao.upsertMostPopularMovies(mostPopularList)
        }
    }

    private suspend fun initTopRated() {
        if (true) {
            val topRatedList = remoteMovieDataSource.loadTopRatedMovies().await()
            moviesDao.upsertTopRatedMovies(topRatedList)
        }
    }
}