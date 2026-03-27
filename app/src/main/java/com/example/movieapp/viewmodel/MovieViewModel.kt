package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.db.MovieEntity
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val movies = repository.getMovies().asLiveData()

    private val _bannerMovies = MutableLiveData<List<MovieEntity>>()
    val bannerMovies: LiveData<List<MovieEntity>> = _bannerMovies

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> = _state

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _state.value = UiState.Loading

            val recommendedResult = repository.refreshRecommendedMovies()
            val bannerResult = repository.getTrendingBannerMovies()

            if (recommendedResult.isSuccess && bannerResult.isSuccess) {
                _bannerMovies.value = bannerResult.getOrNull() ?: emptyList()
                _state.value = UiState.Success
            } else {
                _state.value = UiState.Error("No Internet or API Error")
            }
        }
    }
}