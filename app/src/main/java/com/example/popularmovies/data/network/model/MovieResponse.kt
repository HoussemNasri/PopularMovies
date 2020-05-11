package com.example.popularmovies.data.network.model


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val popularity: Double,
    val id: Long,
    val video: Boolean,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val adult: Boolean,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String
)