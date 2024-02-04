package com.example.quizzazi.presentation.components.game

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.newsapp.utils.Constance
import com.example.quizzazi.presentation.ui.screens.game.TriviaGameViewModel
import com.example.quizzazi.utils.QuizState

@Composable
fun DisplayQuizContent(
    navController: NavHostController,
    viewModel: TriviaGameViewModel,
    quizState: QuizState,
    totalQuestions: Int,
    currentQuestion: Int
) {
    when {
        viewModel.error != null -> DisplayError(viewModel.error!!)
        quizState.isLoading -> DisplayLoadingIndicator()
        else -> DisplayQuizContentDetails(navController, viewModel, quizState, totalQuestions, currentQuestion) {
        }
    }
}

@Composable
private fun DisplayError(error: String) {
    Text(text = "${Constance.ERROR_MESSAGE_PREFIX}$error", color = Color.Red)
}
