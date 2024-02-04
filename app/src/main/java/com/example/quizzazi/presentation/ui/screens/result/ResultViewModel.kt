package com.example.quizzazi.presentation.ui.screens.result


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzazi.domain.model.quiz.ResultModel
import com.example.quizzazi.domain.model.user.User
import com.example.quizzazi.domain.use_cases.user.UserUseCases
import com.example.quizzazi.utils.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val auth: FirebaseAuth, private val userUseCases: UserUseCases) : ViewModel() {

    private val userId = auth.currentUser?.uid
    private val _results = MutableStateFlow<List<ResultModel>>(emptyList())
    val results: StateFlow<List<ResultModel>> = _results.asStateFlow()

    private val _updateScore = MutableStateFlow<Response<Boolean>>(Response.Success(false))

    private val _totalCorrectAnswers = MutableStateFlow(0)
    val totalCorrectAnswers: StateFlow<Int> = _totalCorrectAnswers.asStateFlow()

    private val _totalScore = MutableStateFlow(0.0)
    val totalScore: StateFlow<Double> = _totalScore.asStateFlow()

    private val _totalIncorrectAnswers = MutableStateFlow(0)
    val totalIncorrectAnswers: StateFlow<Int> = _totalIncorrectAnswers.asStateFlow()

    private val _totalPercentage = MutableStateFlow(0.0)
    val totalPercentage: StateFlow<Double> = _totalPercentage.asStateFlow()

    fun updateResults(newResults: List<ResultModel>, totalCorrectAnswers: Int, totalScore: Double, totalIncorrectAnswers: Int, totalPercentage: Double) {
        _results.value = newResults
        _totalCorrectAnswers.value = totalCorrectAnswers
        _totalScore.value = totalScore
        _totalIncorrectAnswers.value = totalIncorrectAnswers
        _totalPercentage.value = totalPercentage
        Log.d("ResultViewModel", "Results updated: $newResults")
    }

    fun updateScore(newScore: Double) {
        if (userId != null) {
            viewModelScope.launch {
                try {
                    val response = userUseCases.updateScoresUseCase(userId, newScore).first()
                    _updateScore.value = response
                } catch (e: Exception) {
                    _updateScore.value = Response.Error("Failed to update scores: ${e.localizedMessage}")
                }
            }
        }
    }

}