package com.example.popularmovies.data.mapper

import com.example.popularmovies.data.db.model.Movie
import com.example.popularmovies.data.network.model.MovieResponse

/***
 * Transform from MovieResponse To Movie
 * */
class MovieMapper(private val movieFilter: MovieFilter) : Mapper<MovieResponse, Movie> {

    override fun transform(f: MovieResponse): Movie =
        Movie(
            movieFilter.id,
            f.id,
            f.originalTitle,
            f.voteCount,
            f.voteAverage,
            f.originalLanguage,
            f.releaseDate,
            f.adult,
            f.overview,
            f.posterPath
        )
}