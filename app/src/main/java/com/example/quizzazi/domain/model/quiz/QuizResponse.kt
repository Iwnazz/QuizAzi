package com.example.quizzz.domain

import java.io.Serializable


data class QuizResponse(
    val response_code: Int,
    val results: List<QuizResult>
)

data class QuizResult(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>,
    var user_answer: String? = null,
    var isAnswerCorrect: Boolean = false
) : Serializable