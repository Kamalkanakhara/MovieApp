package com.example.movieapp.data.repository

import com.example.movieapp.data.db.UserDao
import com.example.movieapp.data.db.UserEntity
import com.example.movieapp.utils.PasswordUtils
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun signUp(email: String, password: String): Result<String> {
        val cleanEmail = email.trim().lowercase()

        if (userDao.isEmailExists(cleanEmail)) {
            return Result.failure(Exception("User already exists"))
        }

        val hashedPassword = PasswordUtils.hashPassword(password)

        userDao.insertUser(
            UserEntity(
                email = cleanEmail,
                passwordHash = hashedPassword
            )
        )

        return Result.success("Sign up successful")
    }

    suspend fun signIn(email: String, password: String): Result<UserEntity> {
        val cleanEmail = email.trim().lowercase()
        val user = userDao.getUserByEmail(cleanEmail)
            ?: return Result.failure(Exception("User not found"))

        val hashedPassword = PasswordUtils.hashPassword(password)

        return if (user.passwordHash == hashedPassword) {
            Result.success(user)
        } else {
            Result.failure(Exception("Invalid password"))
        }
    }
}