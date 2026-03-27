package com.example.movieapp.data.repository

import com.example.movieapp.data.api.MovieApi
import com.example.movieapp.data.api.MovieDetailsResponse
import com.example.movieapp.data.db.MovieDao
import com.example.movieapp.data.db.MovieEntity
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val api: MovieApi,
    private val dao: MovieDao
) {

    companion object {
        const val API_KEY = "b0d651aea4f9cef3d6532233f278fbbf"
    }

    fun getMovies(): Flow<List<MovieEntity>> = dao.getMovies()

    suspend fun refreshRecommendedMovies(): Result<Unit> {
        return try {
            val response = api.getPopularMovies(API_KEY)

            val entities = response.results.map {
                MovieEntity(
                    id = it.id,
                    title = it.title,
                    posterPath = it.poster_path,
                    backdropPath = it.backdrop_path,
                    overview = it.overview,
                    language = it.original_language,
                    releaseDate = it.release_date,
                    rating = it.vote_average
                )
            }

            dao.insertMovies(entities)
            Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun getTrendingBannerMovies(): Result<List<MovieEntity>> {
        return try {
            val response = api.getTrendingMovies(API_KEY)

            val entities = response.results.map {
                MovieEntity(
                    id = it.id,
                    title = it.title,
                    posterPath = it.poster_path,
                    backdropPath = it.backdrop_path,
                    overview = it.overview,
                    language = it.original_language,
                    releaseDate = it.release_date,
                    rating = it.vote_average
                )
            }

            Result.success(entities)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsResponse> {
        return try {
            val response = api.getMovieDetails(
                movieId = movieId,
                apiKey = API_KEY
            )
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}