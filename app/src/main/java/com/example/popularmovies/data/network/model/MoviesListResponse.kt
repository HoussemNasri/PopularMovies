package com.example.popularmovies.data.network.model


import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val movieResponses: List<MovieResponse>
)