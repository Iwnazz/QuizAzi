package com.example.quizzazi.domain.repository

import com.example.quizzazi.domain.model.quiz.CategoryModel
import com.example.quizzazi.domain.model.quiz.ResultModel
import com.example.quizzazi.utils.Response
import com.example.quizzz.domain.QuestionStats
import com.example.quizzz.domain.QuizResponse

interface TriviaRepository {


    suspend fun getResults(): Response<List<ResultModel>>
    suspend fun getCategories(): Response<List<CategoryModel>>
    suspend fun getQuestionStats(): Response<QuestionStats>
    suspend fun getQuiz(amount: Int?, category: Int?, difficulty: String?, type: String?): QuizResponse
}
