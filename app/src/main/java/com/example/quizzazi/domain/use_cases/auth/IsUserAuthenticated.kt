package com.example.quizzazi.domain.use_cases.auth


import com.example.quizzazi.domain.repository.RepositoryAuth
import javax.inject.Inject

class IsUserAuthenticated @Inject constructor(
    private val repositoryAuth: RepositoryAuth
){
   operator fun invoke() = repositoryAuth.isUserAuthenticatedInFirebase()
}