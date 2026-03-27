package com.example.movieapp.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences("movie_app_session", Context.MODE_PRIVATE)

    fun saveLogin(email: String) {
        prefs.edit()
            .putBoolean("is_logged_in", true)
            .putString("logged_in_email", email)
            .apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean("is_logged_in", false)

    fun getLoggedInEmail(): String? = prefs.getString("logged_in_email", null)

    fun logout() {
        prefs.edit().clear().apply()
    }
}