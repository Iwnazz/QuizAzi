package com.example.quizzazi.presentation.components.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.utils.decodeHtmlString
import com.example.quizzazi.R
import com.example.quizzazi.domain.model.quiz.ResultModel

import com.example.quizzazi.utils.getDarkToLightColor

@Composable
fun QuestionItem(result: ResultModel, index: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = getDarkToLightColor()
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
            ) {
                QuestionCircleLabel(questionNumber = index + 1)
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        (decodeHtmlString(htmlEncoded = "${result.question}")),
                        fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.wrapContentWidth(),
                        textAlign = TextAlign.Start,
                        maxLines = 2
                    )
                    CorrectOrNot(userAnswer = result.userAnswer, correctAnswer = result.correctAnswers.toString().length)

                }
                Image(
                    painter = if (result.correctAnswers == 1) {
                        painterResource(id = R.drawable.check)
                    } else {
                        painterResource(
                            id = R.drawable.incorrect
                        )
                    },
                    contentDescription = if (result.correctAnswers == 1) " Question Success" else " Question Wrong",
                    modifier = Modifier
                        .size(24.dp) // Adjust the size according to your preference
                        .padding(start = 8.dp) // Add padding as needed
                )

            }
        }
    }
}
