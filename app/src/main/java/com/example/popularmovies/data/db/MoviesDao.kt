package com.example.popularmovies.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.popularmovies.data.db.model.Movie
import com.example.popularmovies.data.mapper.MovieFilter


@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMostPopularMovies(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTopRatedMovies(movies: List<Movie>)

    @Query("SELECT * FROM movietable WHERE contentId = :mostPopularId")
    fun getMostPopularMovies(mostPopularId: Int = MovieFilter.MOST_POPULAR.id): LiveData<List<Movie>>

    @Query("SELECT * FROM movietable WHERE contentId = :topRatedId")
    fun getTopRatedMovies(topRatedId: Int = MovieFilter.TOP_RATED.id): LiveData<List<Movie>>

    @Query("SELECT * FROM movietable")
    fun getAllMovies(): LiveData<List<Movie>>

}