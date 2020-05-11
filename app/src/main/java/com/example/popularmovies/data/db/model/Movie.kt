package com.example.popularmovies.data.db.model

import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "MovieTable", primaryKeys = ["contentId", "movieId"])
data class Movie(
    val contentId: Int,
    val movieId: Long,
    val movieTitle: String,
    val movieVoteCount: Int,
    val movieVoteAverage: Double,
    val movieLanguage: String,
    val movieReleaseDate: String,
    val movieForAdult: Boolean,
    val movieOverview: String,
    val moviePosterUrl: String

) {
    override fun toString(): String {
        return "Movie(contentId=$contentId, movieId=$movieId, movieTitle='$movieTitle', movieVoteCount=$movieVoteCount, movieVoteAverage=$movieVoteAverage, movieLanguage='$movieLanguage', movieReleaseDate='$movieReleaseDate', movieForAdult=$movieForAdult, movieOverview='$movieOverview', moviePosterUrl='$moviePosterUrl')"
    }

}