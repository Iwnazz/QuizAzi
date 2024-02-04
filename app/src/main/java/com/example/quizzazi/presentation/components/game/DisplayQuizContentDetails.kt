package com.example.quizzazi.presentation.components.game

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.quizzazi.R
import com.example.quizzazi.presentation.components.game.CountdownTimer
import com.example.quizzazi.presentation.ui.screens.game.TriviaGameViewModel
import com.example.quizzazi.utils.QuizState
import com.example.quizzazi.presentation.ui.nav.Screens
import com.example.quizzazi.utils.getBlackToWhiteColor


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DisplayQuizContentDetails(
    navController: NavHostController,
    viewModel: TriviaGameViewModel,
    quizState: QuizState,
    totalQuestions: Int,
    currentQuestion: Int,
    onTimerFinished: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(getBlackToWhiteColor())
    ) {

        InformationOfQuiz(
            currentQuestion = currentQuestion,
            totalQuestions = totalQuestions
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            CountdownTimer(
                initialTime = viewModel.elapsedTimeFlow.value,
                elapsedTimeFlow = viewModel.elapsedTimeFlow,
                quizState = quizState,
                onTimerFinish = {
                    viewModel.calculateAndSetResults()
                    navController.navigate(Screens.ResultScreen.route) {
                        popUpTo(Screens.TriviaGameScreen.route) {
                            inclusive = true
                        }
                    }
                },
                nextQuest = {
                    viewModel.onNextClicked()
                }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            quizState.quiz?.results?.getOrNull(quizState.currentQuestionIndex)
                ?.let { question ->
                    QuizQuestionItem(
                        viewModel = viewModel,
                        question = question,
                        quizState = quizState,
                        onNextClicked = {
                            viewModel.calculateAndSetResults()
                            if (quizState.currentQuestionIndex == totalQuestions - 1) {
                                navController.navigate(Screens.ResultScreen.route) {
                                    popUpTo(Screens.TriviaGameScreen.route) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                viewModel.onNextClicked()
                            }
                        }
                    )
                }
        }
    }
}