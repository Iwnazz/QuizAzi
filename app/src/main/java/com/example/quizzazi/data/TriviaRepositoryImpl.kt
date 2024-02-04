package com.example.quizzazi.data

import com.example.newsapp.utils.getCategoryItemList
import com.example.quizzazi.data.local.QuizService
import com.example.quizzazi.domain.model.quiz.CategoryModel
import com.example.quizzazi.domain.model.quiz.ResultModel

import com.example.quizzazi.domain.repository.TriviaRepository
import com.example.quizzazi.utils.Response
import com.example.quizzz.domain.QuestionStats
import com.example.quizzz.domain.QuizResponse
import javax.inject.Inject

class TriviaRepositoryImpl @Inject constructor(
    private val quizService: QuizService
) : TriviaRepository {
    override suspend fun getResults(): Response<List<ResultModel>> {
        TODO("Not yet implemented")
    }


    override suspend fun getCategories(): Response<List<CategoryModel>> {
        return try {
            val result = getCategoryItemList()
            Response.success(result)
        } catch (e: Exception) {
            Response.error("Failed to fetch categories", e)
        }
    }

    override suspend fun getQuestionStats(): Response<QuestionStats> {
        return try {
            val result = quizService.getData()
            Response.success(result)
        } catch (e: Exception) {
            Response.error("Failed to fetch question stats", e)
        }
    }

    override suspend fun getQuiz(amount: Int?, category: Int?, difficulty: String?, type: String?): QuizResponse {
        return try {
            quizService.getQuiz(amount, category, difficulty, type)
        } catch (e: Exception) {
            throw RuntimeException("Failed to fetch quiz: ${e.message}", e)
        }
    }
}
