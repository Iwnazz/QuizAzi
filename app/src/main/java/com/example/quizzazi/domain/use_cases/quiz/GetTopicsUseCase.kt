package com.example.quizzazi.domain.use_cases.quiz

import com.example.quizzazi.domain.model.quiz.CategoryModel
import com.example.quizzazi.domain.repository.TriviaRepository
import com.example.quizzazi.utils.Response
import javax.inject.Inject

class GetTopicsUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {
    suspend operator fun invoke(): Response<List<CategoryModel>> {
        return triviaRepository.getCategories()
    }
}