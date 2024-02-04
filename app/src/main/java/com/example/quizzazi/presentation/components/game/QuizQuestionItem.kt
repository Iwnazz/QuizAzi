package com.example.quizzazi.presentation.components.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.utils.Constance
import com.example.newsapp.utils.Constance.TOTAL_QUESTIONS
import com.example.newsapp.utils.decodeHtmlString
import com.example.quizzazi.R
import com.example.quizzazi.presentation.theme.BeautifulGreen
import com.example.quizzazi.presentation.ui.screens.game.TriviaGameViewModel
import com.example.quizzazi.utils.QuizState
import com.example.quizzazi.utils.getDarkToLightColor
import com.example.quizzz.domain.QuizResult

@Composable
fun QuizQuestionItem(
    viewModel:TriviaGameViewModel,
    question: QuizResult,
    quizState: QuizState,
    onNextClicked: () -> Unit,
) {
    var isAnswerSelected by remember { mutableStateOf(false) }
    LaunchedEffect(quizState.currentQuestionIndex) {
        isAnswerSelected = false
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = decodeHtmlString(question.category),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = getDarkToLightColor()
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 24.dp
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = decodeHtmlString(question.question),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(Constance.MediumTwentyFourDp))

        val shuffledAnswers = remember(question) {
            val answers = mutableListOf(question.correct_answer)
            answers.addAll(question.incorrect_answers)
            answers.shuffle()
            answers
        }
        shuffledAnswers.forEach { answer ->
            AnswerOptionButton(
                answer = decodeHtmlString(answer),
                selected = quizState.selectedAnswer == answer,
                correct = question.correct_answer == answer,
                onAnswerSelected = {
                    if (!isAnswerSelected) {
                        isAnswerSelected = true
                        viewModel.onAnswerSelected(it)
                    }
                }
            )
        }

        if (isAnswerSelected) {
            Text(
                text = "Correct answer: ${decodeHtmlString(question.correct_answer)}",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                fontWeight = FontWeight.Bold,
                color = BeautifulGreen,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(Constance.MediumTwentyFourDp))

        Button(
            onClick = { if (!isAnswerSelected){

            }else{
                onNextClicked()
            }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .wrapContentHeight()
                .defaultMinSize(minHeight = 54.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(if (!isAnswerSelected){
                Color.Gray
            }else{
                getDarkToLightColor()
            })
        ) {
            Text(
                text = if(quizState.currentQuestionIndex == TOTAL_QUESTIONS - 1){
                    "Result"
                } else {
                    "Next"
                },
                textAlign = TextAlign.Center,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}