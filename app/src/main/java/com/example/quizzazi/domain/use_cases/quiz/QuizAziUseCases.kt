package com.example.quizzazi.domain.use_cases.quiz

data class QuizAziUseCases (
    val getTopicsUseCase: GetTopicsUseCase,
    val getQuestionsStatsUseCase: GetQuestionsStatsUseCase,
    val getQuizUseCase: GetQuizUseCase
)