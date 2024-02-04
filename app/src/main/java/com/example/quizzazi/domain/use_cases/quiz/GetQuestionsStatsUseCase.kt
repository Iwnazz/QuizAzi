package com.example.quizzazi.domain.use_cases.quiz

import com.example.quizzazi.domain.repository.TriviaRepository
import com.example.quizzazi.utils.Response
import com.example.quizzz.domain.QuestionStats
import javax.inject.Inject

class GetQuestionsStatsUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {
    suspend operator fun invoke(): Response<QuestionStats> {
        return triviaRepository.getQuestionStats()
    }
}
