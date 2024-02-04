package com.example.quizzazi.presentation.components.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsapp.utils.ReusableCard

@Composable
fun FirstDetails(totalCorrectAnswers: String, totalPercentage: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ReusableCard(
            modifier = Modifier.weight(1f),
            labelText = "Correct",
            questionCount = "$totalCorrectAnswers Questions",
            circleColor = Color.Green
        )
        ReusableCard(
            modifier = Modifier.weight(1f),
            labelText = "Total Percentage",
            questionCount = "$totalPercentage %",
            circleColor = Color.Blue
        )
    }
}

@Composable
fun SecondDetails(totalScore: String, totalIncorrectAnswers: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ReusableCard(
            modifier = Modifier.weight(1f),
            labelText = "Score",
            questionCount = "$totalScore Score",
            circleColor = Color.Yellow
        )
        ReusableCard(
            modifier = Modifier.weight(1f),
            labelText = "Incorrect",
            questionCount = "$totalIncorrectAnswers Question",
            circleColor = Color.Red
        )
    }
}