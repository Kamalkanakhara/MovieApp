package com.example.movieapp.data.api

data class MovieDetailsResponse(
    val id: Int,
    val title: String,
    val overview: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val release_date: String?,
    val runtime: Int?,
    val vote_average: Double?,
    val genres: List<GenreDto>?,
    val spoken_languages: List<SpokenLanguageDto>?,
    val credits: CreditsDto?,
    val videos: VideosDto?,
    val release_dates: ReleaseDatesDto?
)

data class GenreDto(
    val id: Int,
    val name: String
)

data class SpokenLanguageDto(
    val english_name: String?,
    val name: String?
)

data class CreditsDto(
    val cast: List<CastDto>?
)

data class CastDto(
    val id: Int,
    val name: String?,
    val character: String?,
    val profile_path: String?
)

data class VideosDto(
    val results: List<VideoDto>?
)

data class VideoDto(
    val key: String?,
    val name: String?,
    val site: String?,
    val type: String?
)

data class ReleaseDatesDto(
    val results: List<ReleaseDateCountryDto>?
)

data class ReleaseDateCountryDto(
    val iso_3166_1: String?,
    val release_dates: List<ReleaseDateItemDto>?
)

data class ReleaseDateItemDto(
    val certification: String?,
    val type: Int?
)