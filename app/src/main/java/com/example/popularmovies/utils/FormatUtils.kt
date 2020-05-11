package com.example.popularmovies.utils

import com.example.popularmovies.data.db.model.Movie

fun formatMovieObject(movie: Movie): String {
    var formatted = "->\n "
    val tostr = movie.toString()

    tostr.forEach {
        formatted = formatted.plus(it)
        if (it == ',')
            formatted = formatted.plus("\n")
    }

    return formatted

}