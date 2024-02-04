package com.example.quizzazi.domain.repository


import com.example.quizzazi.utils.Response
import kotlinx.coroutines.flow.Flow

interface RepositoryAuth {

    fun isUserAuthenticatedInFirebase(): Boolean

    suspend fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>>

    fun firebaseSignOut(): Flow<Response<Boolean>>

    suspend fun firebaseSignUp(email: String, password: String, userName: String): Flow<Response<Boolean>>
}
