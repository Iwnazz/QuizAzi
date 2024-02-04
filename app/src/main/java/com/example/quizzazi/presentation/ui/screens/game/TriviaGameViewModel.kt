package com.example.quizzazi.presentation.ui.screens.game


import android.os.SystemClock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.utils.Constance.INITIAL_TIME
import com.example.newsapp.utils.Constance.TOTAL_QUESTIONS
import com.example.quizzazi.domain.model.quiz.ResultModel
import com.example.quizzazi.domain.model.quiz.toResultModel
import com.example.quizzazi.domain.use_cases.quiz.QuizAziUseCases
import com.example.quizzazi.presentation.ui.screens.result.ResultViewModel
import com.example.quizzazi.utils.QuizState
import com.example.quizzz.domain.QuizResponse
import com.example.quizzz.domain.QuizResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

@Suppress("NAME_SHADOWING")
@HiltViewModel
class TriviaGameViewModel @Inject constructor(
    private val quizAziUseCases: QuizAziUseCases
) : ViewModel() {

    private var _quiz by mutableStateOf<QuizResponse?>(null)
    val quiz: QuizResponse?
        get() = _quiz

    private var _quizState by mutableStateOf(QuizState())
    val quizState: QuizState
        get() = _quizState

    private var _error by mutableStateOf<String?>(null)
    val error: String?
        get() = _error

    @Inject
    lateinit var resultViewModel: ResultViewModel

    private val _elapsedTimeFlow = MutableStateFlow(INITIAL_TIME)
    var elapsedTimeFlow: StateFlow<Long> = _elapsedTimeFlow.asStateFlow()

    private var timer: Timer? = null

    private var remainingTime = INITIAL_TIME

    init {
        startTimer()
    }

    private fun startTimer() {
        timer = fixedRateTimer(period = 1000L, initialDelay = 0L) {
            viewModelScope.launch {
                withContext(Dispatchers.Main) {
                    remainingTime -= 1000L
                    if (remainingTime <= 0) {
                        onAnswerSelected(null)
                        onNextClicked()
                        restartTimer()
                    }
                }
            }
        }
    }
    fun fetchQuiz(amount: Int?, category: Int?, difficulty: String?, type: String?) {
        viewModelScope.launch {
            try {
                _quizState = _quizState.copy(isLoading = true)
                withContext(Dispatchers.IO) {
                    _quiz = quizAziUseCases.getQuizUseCase(amount, category, difficulty, type)
                }
                withContext(Dispatchers.Main) {
                    _quizState = _quizState.copy(isLoading = false, quiz = _quiz)
                }
            } catch (e: Exception) {
                handleError("Failed to fetch quiz", e)
            }
        }
    }

    fun onAnswerSelected(answer: String?) {
        _quizState = _quizState.copy(selectedAnswer = answer)
        _quizState.quiz?.results?.getOrNull(_quizState.currentQuestionIndex)?.let { result ->
            result.user_answer = answer
            result.isAnswerCorrect = answer.equals(result.correct_answer, ignoreCase = true)
            _quizState.quiz?.results?.forEachIndexed { index, result ->
                result.isAnswerCorrect = result.isAnswerCorrect || (index == _quizState.currentQuestionIndex && answer.equals(result.correct_answer, ignoreCase = true))
            }
        }
    }

    private fun stopTimer() {
        timer?.cancel()
    }


    private fun restartTimer() {
        stopTimer()
        remainingTime = INITIAL_TIME
        startTimer()
    }

    fun onNextClicked() {
        val currentQuestionIndex = quizState.currentQuestionIndex
        val totalQuestions = TOTAL_QUESTIONS

        if (currentQuestionIndex < totalQuestions - 1) {
            _quizState = _quizState.copy(
                selectedAnswer = null,
                currentQuestionIndex = currentQuestionIndex + 1
            )
            restartTimer()
        }
    }

    fun calculateAndSetResults() {
        viewModelScope.launch {
            val results = calculateResults()
            val totalCorrectAnswers = results.sumBy { it.correctAnswers }
            val totalIncorrectAnswers = results.sumBy { it.incorrectAnswers }
            val totalScore = results.sumByDouble { it.score }
            val totalPercentage = calculateTotalPercentage(results)
            resultViewModel.updateResults(results, totalCorrectAnswers, totalScore, totalIncorrectAnswers, totalPercentage)
        }
    }

    private suspend fun calculateResults(): List<ResultModel> = withContext(Dispatchers.Default) {
        _quizState.quiz?.results?.mapIndexed { index, result ->
            val elapsedTimeForQuestion = calculateElapsedTimeForQuestion(index + 1)
            result.toResultModel(elapsedTimeForQuestion)
        } ?: emptyList()
    }

    private fun calculateElapsedTimeForQuestion(index: Int): Long {
        val timeBetweenQuestions = INITIAL_TIME
        val elapsedTimeForQuestion = SystemClock.elapsedRealtime() - (index * timeBetweenQuestions)
        return elapsedTimeForQuestion.takeIf { it >= 0 } ?: 0L
    }

    private fun handleError(message: String, exception: Exception) {
        _error = "$message: ${exception.message}"
        _quizState = _quizState.copy(isLoading = false)
    }

    private fun calculateTotalPercentage(results: List<ResultModel>): Double {
        val totalQuestions = results.size
        val totalCorrectAnswers = results.sumBy { if (it.correctAnswers > 0) 1 else 0 }
        return (totalCorrectAnswers.toDouble() / totalQuestions.toDouble()) * 100.0
    }
}