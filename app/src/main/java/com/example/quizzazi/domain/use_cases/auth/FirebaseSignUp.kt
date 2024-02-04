package com.example.quizzazi.domain.use_cases.auth


import com.example.quizzazi.domain.repository.RepositoryAuth
import com.example.quizzazi.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val repositoryAuth: RepositoryAuth
) {
    suspend operator fun invoke(email: String, password: String, userName: String): Flow<Response<Boolean>> =
        repositoryAuth.firebaseSignUp(email, password, userName)
}