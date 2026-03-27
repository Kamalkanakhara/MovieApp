package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.MovieDetailsResponse
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.ui.details.CastUiModel
import com.example.movieapp.ui.details.MovieDetailsUiModel
import com.example.movieapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> = _state

    private val _movieDetails = MutableLiveData<MovieDetailsUiModel>()
    val movieDetails: LiveData<MovieDetailsUiModel> = _movieDetails

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _state.value = UiState.Loading

            val result = repository.getMovieDetails(movieId)

            if (result.isSuccess) {
                val response = result.getOrNull()
                if (response != null) {
                    _movieDetails.value = response.toUiModel()
                    _state.value = UiState.Success
                } else {
                    _state.value = UiState.Error("Movie details not found")
                }
            } else {
                _state.value = UiState.Error("Failed to load movie details")
            }
        }
    }

    private fun MovieDetailsResponse.toUiModel(): MovieDetailsUiModel {
        val certificationValue = extractCertification("IN").ifBlank { "N/A" }

        val trailer = videos?.results
            ?.firstOrNull {
                it.site.equals("YouTube", true) &&
                        it.type.equals("Trailer", true) &&
                        !it.key.isNullOrBlank()
            }

        return MovieDetailsUiModel(
            id = id,
            title = title,
            backdropPath = backdrop_path,
            posterPath = poster_path,
            overview = overview ?: "No story plot available.",
            genreText = genres
                ?.take(2)
                ?.joinToString(", ") { it.name }
                .orEmpty()
                .ifBlank { "Movie" },
            certification = certificationValue,
            durationText = runtime.toDurationText(),
            releaseDateText = release_date.orEmpty().ifBlank { "N/A" },
            languageText = spoken_languages
                ?.mapNotNull { it.english_name ?: it.name }
                ?.distinct()
                ?.take(2)
                ?.joinToString(", ")
                .orEmpty()
                .ifBlank { "N/A" },
            cast = credits?.cast
                ?.take(10)
                ?.map {
                    CastUiModel(
                        id = it.id,
                        name = it.name ?: "Unknown",
                        character = it.character ?: "",
                        profilePath = it.profile_path
                    )
                }
                ?: emptyList(),
            trailerKey = trailer?.key
        )
    }

    private fun Int?.toDurationText(): String {
        if (this == null || this <= 0) return "N/A"
        val hours = this / 60
        val minutes = this % 60
        return if (hours > 0) "${hours}hr:${minutes}min" else "${minutes}min"
    }

    private fun MovieDetailsResponse.extractCertification(countryCode: String): String {
        return release_dates?.results
            ?.firstOrNull { it.iso_3166_1.equals(countryCode, true) }
            ?.release_dates
            ?.firstOrNull { !it.certification.isNullOrBlank() }
            ?.certification
            .orEmpty()
    }
}