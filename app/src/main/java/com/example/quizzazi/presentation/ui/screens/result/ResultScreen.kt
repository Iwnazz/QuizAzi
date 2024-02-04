package com.example.quizzazi.presentation.ui.screens.result

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizzazi.presentation.components.result.AnimatedPieChart
import com.example.quizzazi.presentation.components.result.FirstDetails
import com.example.quizzazi.presentation.components.result.QuestionItem
import com.example.quizzazi.presentation.components.result.SecondDetails
import com.example.quizzazi.presentation.ui.nav.Screens
import com.example.quizzazi.utils.getDarkToLightColor
import java.util.concurrent.TimeUnit


@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: ResultViewModel = hiltViewModel()
) {

    val resultsState = viewModel.results.collectAsState(initial = emptyList())
    val totalCorrectAnswers = viewModel.totalCorrectAnswers.collectAsState().value
    val totalIncorrectAnswers = viewModel.totalIncorrectAnswers.collectAsState().value
    val totalScore = viewModel.totalScore.collectAsState().value
    val totalPercentage = viewModel.totalPercentage.collectAsState().value
    val updatedResults = resultsState.value
    viewModel.updateScore(totalScore)

    LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
        item {
            AnimatedPieChart(
                correctPercentage = totalPercentage.toInt(),
                incorrectPercentage = (100 - totalPercentage).toInt()
            )
        }
        item {
            FirstDetails(
                totalCorrectAnswers = totalCorrectAnswers.toString(),
                totalPercentage = totalPercentage.toString())
        }
        item {
            SecondDetails(
                totalScore = totalScore.toString(),
                totalIncorrectAnswers = totalIncorrectAnswers.toString())
        }
        if (updatedResults.isNotEmpty()) {
            items(updatedResults) { result ->
                QuestionItem(result = result, index = updatedResults.indexOf(result) + 1)
            }
        } else {
            item {
                Text("No results available.")
            }
        }
        item{
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = getDarkToLightColor()
                ),
                onClick = {
                    navController.navigate(Screens.TriviaScreen.route){
                        popUpTo(Screens.TriviaGameScreen.route){
                            inclusive = true
                        }
                        popUpTo(Screens.ResultScreen.route){
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {}
        }
    }
}

    fun formatTime(timeInMillis: Long): String {
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeInMillis)
        return "$seconds seconds"
    }

