package com.arifbm.movieapp.data.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val result: List<Movie>
)
