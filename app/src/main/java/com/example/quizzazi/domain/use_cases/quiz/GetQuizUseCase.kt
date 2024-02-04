package com.example.quizzazi.domain.use_cases.quiz


import com.example.quizzazi.domain.repository.TriviaRepository
import com.example.quizzazi.utils.Response
import com.example.quizzz.domain.QuizResponse
import javax.inject.Inject

class GetQuizUseCase @Inject constructor(private val triviaRepository: TriviaRepository) {
    suspend operator fun invoke(
        amount: Int?,
        category: Int?,
        difficulty: String?,
        type: String?
    ): QuizResponse {
        return triviaRepository.getQuiz(amount, category, difficulty, type)
    }
}