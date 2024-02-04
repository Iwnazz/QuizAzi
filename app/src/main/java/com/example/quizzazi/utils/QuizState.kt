package com.example.quizzazi.utils

import com.example.quizzz.domain.QuizResponse

data class QuizState(
    val isLoading: Boolean = false,
    val quiz: QuizResponse? = null,
    var selectedAnswer: String? = null,
    val currentQuestionIndex: Int = 0
)