package com.example.quizzazi.data.local

import com.example.quizzz.domain.QuestionStats
import com.example.quizzz.domain.QuizResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizService {
    @GET("api.php")
    suspend fun getQuiz(
        @Query("amount") amount: Int?,
        @Query("category") category: Int?,
        @Query("difficulty") difficulty: String?,
        @Query("type") type: String?
    ): QuizResponse

    @GET("api_count_global.php")
    suspend fun getData(): QuestionStats
}