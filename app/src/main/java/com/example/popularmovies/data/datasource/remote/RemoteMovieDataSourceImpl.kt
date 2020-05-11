package com.example.popularmovies.data.datasource.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.data.network.model.MoviesListResponse
import com.example.popularmovies.data.network.service.MovieService
import com.example.popularmovies.data.datasource.MovieDataSource
import com.example.popularmovies.data.db.model.Movie
import com.example.popularmovies.data.mapper.MovieFilter
import com.example.popularmovies.data.mapper.MovieMapper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class RemoteMovieDataSourceImpl(
    private val movieService: MovieService
) : RemoteMovieDataSource {
    private val TAG = "__RemoteMovieDataSource"

    override suspend fun loadTopRatedMovies(): Deferred<List<Movie>> {
        Log.d(TAG, "loadTopRatedMovies")
        return withContext(Dispatchers.IO) {
            async {
                val topRatedMoviesResponse = movieService.getTopRatedMovies().await()
                Log.d(TAG, "from remote data source")
                return@async mapToMovie(topRatedMoviesResponse, MovieFilter.TOP_RATED)
            }
        }

    }

    override suspend fun loadMostPopularMovies(): Deferred<List<Movie>> {
        return withContext(Dispatchers.IO) {
            async {
                val mostPopularMoviesResponse = movieService.getMostPopularMovies().await()
                Log.d(TAG, "from remote data source")
                return@async mapToMovie(mostPopularMoviesResponse, MovieFilter.MOST_POPULAR)
            }
        }
    }

    override suspend fun loadMovieDetails() {
        TODO("Not yet implemented")
    }

    private fun mapToMovie(
        moviesListResponse: MoviesListResponse,
        filter: MovieFilter
    ): List<Movie> {
        val movieResponseList = moviesListResponse.movieResponses
        val movieList = ArrayList<Movie>()
        for (item in movieResponseList) {
            movieList.add(MovieMapper(filter).transform(item))
        }
        return movieList
    }
}