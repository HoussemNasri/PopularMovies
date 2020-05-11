package com.example.popularmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.popularmovies.R
import com.example.popularmovies.data.network.model.MovieResponse
import com.example.popularmovies.data.network.service.MovieService
import com.example.popularmovies.data.datasource.remote.RemoteMovieDataSourceImpl
import com.example.popularmovies.data.db.MovieDatabase
import com.example.popularmovies.data.db.model.Movie
import com.example.popularmovies.data.repository.MovieRepositoryImpl
import com.example.popularmovies.internal.lazyDeferred
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    private val TAG = "__MainActivity"
    private var nextItem = 0
    var moviesList = MutableLiveData<List<MovieResponse>>()
    var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()

        // Remote
        val movieService = MovieService()
        val remoteMovieDataSource = RemoteMovieDataSourceImpl(movieService)

        //Local
        val database = MovieDatabase(this)


        val repository = MovieRepositoryImpl(database.movieDao(), remoteMovieDataSource)


        val mostPopularMovies by lazyDeferred {
            repository.getMostPopular()
        }

        val topRatedMovies by lazyDeferred {
            repository.getTopRatedMovies()
        }

        nextMovie.setOnClickListener {
            Log.d(TAG, "Fetching data ...")
            fetchData(mostPopularMovies, topRatedMovies)
        }

    }

    private fun fetchData(
        mostPopularMovies: Deferred<LiveData<List<Movie>>>,
        topRatedMovies: Deferred<LiveData<List<Movie>>>
    ) = launch {
        Log.d(TAG, "isJobCompleted : ${mostPopularMovies.isCompleted}")
        val mostPopular = mostPopularMovies.await()
        val topRated = topRatedMovies.await()

        mostPopular.observe(this@MainActivity, Observer {
            Log.d(TAG, "Most Popular : ${it.size} -> ${it[0]}")
        })

        topRated.observe(this@MainActivity, Observer {
            Log.d(TAG, "Top Rated : ${it.size} -> ${it[0]}")
        })
    }


}
