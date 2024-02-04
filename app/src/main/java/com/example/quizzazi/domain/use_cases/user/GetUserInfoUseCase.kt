package com.example.quizzazi.domain.use_cases.user

import com.example.quizzazi.domain.repository.UserRepository
import com.example.quizzazi.utils.Response
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke (userId: String) = userRepository.getUserInfo(userId)
}