package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repository.AuthRepository
import com.example.movieapp.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _authState = MutableLiveData<AuthState>(AuthState.Idle)
    val authState: LiveData<AuthState> = _authState

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val result = authRepository.signUp(email, password)
            if (result.isSuccess) {
                sessionManager.saveLogin(email)
                _authState.value = AuthState.Success("Sign up successful")
            } else {
                _authState.value = AuthState.Error(
                    result.exceptionOrNull()?.message ?: "Sign up failed"
                )
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val result = authRepository.signIn(email, password)
            if (result.isSuccess) {
                sessionManager.saveLogin(email)
                _authState.value = AuthState.Success("Sign in successful")
            } else {
                _authState.value = AuthState.Error(
                    result.exceptionOrNull()?.message ?: "Sign in failed"
                )
            }
        }
    }
    fun getLoggedInEmail(): String? {
        return sessionManager.getLoggedInEmail()
    }
    fun isLoggedIn(): Boolean = sessionManager.isLoggedIn()

    fun logout() = sessionManager.logout()
}