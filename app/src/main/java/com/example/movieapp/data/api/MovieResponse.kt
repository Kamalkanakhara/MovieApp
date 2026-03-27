package com.example.movieapp.data.api

data class MovieResponse(
    val results: List<MovieDto>
)

data class MovieDto(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val overview: String?,
    val original_language: String?,
    val release_date: String?,
    val vote_average: Double?
)