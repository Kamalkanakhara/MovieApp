package com.example.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.api.MovieApi
import com.example.movieapp.data.api.RetrofitInstance
import com.example.movieapp.data.db.MovieDao
import com.example.movieapp.data.db.MovieDatabase
import com.example.movieapp.data.db.UserDao
import com.example.movieapp.data.repository.AuthRepository
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.utils.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApi(): MovieApi = RetrofitInstance.api

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDao(db: MovieDatabase): MovieDao = db.movieDao()

    @Provides
    fun provideUserDao(db: MovieDatabase): UserDao = db.userDao()

    @Provides
    fun provideMovieRepository(
        api: MovieApi,
        dao: MovieDao
    ): MovieRepository = MovieRepository(api, dao)

    @Provides
    fun provideAuthRepository(
        userDao: UserDao
    ): AuthRepository = AuthRepository(userDao)

    @Provides
    @Singleton
    fun provideSessionManager(
        @ApplicationContext context: Context
    ): SessionManager = SessionManager(context)
}