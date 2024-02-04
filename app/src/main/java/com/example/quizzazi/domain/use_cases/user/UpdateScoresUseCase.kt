package com.example.quizzazi.domain.use_cases.user

import com.example.quizzazi.domain.repository.UserRepository
import javax.inject.Inject

class UpdateScoresUseCase  @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: String, newScore: Double) =
        userRepository.updateScores(userId, newScore)
}