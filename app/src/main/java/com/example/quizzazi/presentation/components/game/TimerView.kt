package com.example.quizzazi.presentation.components.game

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.utils.Constance.INITIAL_TIME
import com.example.newsapp.utils.Constance.TOTAL_QUESTIONS
import com.example.quizzazi.R
import com.example.quizzazi.presentation.theme.BeautifulGreen
import com.example.quizzazi.presentation.theme.BeautifulRed
import com.example.quizzazi.presentation.theme.MyYellow
import com.example.quizzazi.utils.QuizState
import com.example.quizzazi.utils.getDarkToLightColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlin.concurrent.fixedRateTimer

@SuppressLint("AutoboxingStateCreation")
@Composable
fun CountdownTimer(
    initialTime: Long,
    elapsedTimeFlow: StateFlow<Long>,
    quizState: QuizState,
    onTimerFinish: () -> Unit,
    nextQuest: () -> Unit
) {
    var elapsedTime by remember { mutableStateOf(initialTime) }
    val isTimerRunning by remember { mutableStateOf(true) }

    val countDownTimer = remember {
        object : CountDownTimer(elapsedTime, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                elapsedTime = millisUntilFinished
            }

            override fun onFinish() {
                if (quizState.currentQuestionIndex == TOTAL_QUESTIONS - 1) {
                    onTimerFinish()
                }else{
                    nextQuest()
                }
            }
        }
    }

    DisposableEffect(isTimerRunning) {
        if (isTimerRunning) {
            countDownTimer.start()
        } else {
            countDownTimer.cancel()
        }

        onDispose {
            countDownTimer.cancel()
        }
    }

    val progress by animateFloatAsState(
        targetValue = if (elapsedTime > 0) 1f - (elapsedTime.toFloat() / initialTime.toFloat()) else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    Box(
        modifier = Modifier
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.size(60.dp),
            color = calculateProgressColor(elapsedTime)
        )

        Text(
            text = (elapsedTime / 1000).toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }

    LaunchedEffect(quizState.currentQuestionIndex, quizState.quiz) {
        if (quizState.currentQuestionIndex < (quizState.quiz?.results?.size ?: 0)) {
            elapsedTime = calculateElapsedTimeForQuestion(quizState.currentQuestionIndex + 1)
            countDownTimer.start()
        } else {
            elapsedTime = initialTime
        }
    }

    // Используйте collect для получения актуальных значений из StateFlow
    LaunchedEffect(elapsedTimeFlow) {
        elapsedTimeFlow.collect { newElapsedTime ->
            elapsedTime = newElapsedTime
        }
    }
}

private fun calculateElapsedTimeForQuestion(index: Int): Long {
    val timeBetweenQuestions = INITIAL_TIME
    val elapsedTimeForQuestion = SystemClock.elapsedRealtime() - ((index - 1) * timeBetweenQuestions)
    return (timeBetweenQuestions - elapsedTimeForQuestion).takeIf { it >= 0 } ?: 0L
}
@Composable
private fun calculateProgressColor(elapsedTime: Long): Color {
    return when {
        elapsedTime > 10000 -> getDarkToLightColor()
        elapsedTime > 4000 -> MyYellow
        else -> BeautifulRed
    }
}