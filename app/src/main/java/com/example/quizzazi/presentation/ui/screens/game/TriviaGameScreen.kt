package com.example.quizzazi.presentation.ui.screens.game

import android.annotation.SuppressLint

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsapp.utils.Constance.TOTAL_QUESTIONS
import com.example.quizzazi.presentation.components.game.DisplayQuizContent


@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@Composable
fun TriviaGameScreen(
    navController: NavHostController,
    categoryId: String,
    viewModel: TriviaGameViewModel = hiltViewModel(),
) {
    checkAndFetchQuizData(viewModel, categoryId)

    val quizState = viewModel.quizState
    val totalQuestions = TOTAL_QUESTIONS
    val currentQuestion = quizState.currentQuestionIndex + 1

        DisplayQuizContent(navController, viewModel, quizState, totalQuestions, currentQuestion)
    }



private fun checkAndFetchQuizData(viewModel: TriviaGameViewModel, categoryId: String) {
    if (viewModel.quizState.quiz == null && !viewModel.quizState.isLoading && viewModel.error == null) {
        viewModel.fetchQuiz(
            amount = TOTAL_QUESTIONS,
            category = categoryId.toIntOrNull(),
            difficulty = null,
            type = null
        )
    }
}
