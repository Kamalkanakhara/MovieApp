package com.example.movieapp.ui.details

data class MovieDetailsUiModel(
    val id: Int,
    val title: String,
    val backdropPath: String?,
    val posterPath: String?,
    val overview: String,
    val genreText: String,
    val certification: String,
    val durationText: String,
    val releaseDateText: String,
    val languageText: String,
    val cast: List<CastUiModel>,
    val trailerKey: String?
)

data class CastUiModel(
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String?
)