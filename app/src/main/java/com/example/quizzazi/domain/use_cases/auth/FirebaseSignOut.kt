package com.example.quizzazi.domain.use_cases.auth


import com.example.quizzazi.domain.repository.RepositoryAuth
import com.example.quizzazi.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseSignOut @Inject constructor(
    private val repositoryAuth: RepositoryAuth
) {
    operator fun invoke(): Flow<Response<Boolean>> = repositoryAuth.firebaseSignOut()
}