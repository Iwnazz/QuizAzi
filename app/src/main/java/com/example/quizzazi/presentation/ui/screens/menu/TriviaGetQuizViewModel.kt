package com.example.quizzazi.presentation.ui.screens.menu

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzazi.domain.model.quiz.CategoryModel
import com.example.quizzazi.domain.model.user.User
import com.example.quizzazi.domain.use_cases.quiz.QuizAziUseCases
import com.example.quizzazi.domain.use_cases.user.UserUseCases
import com.example.quizzazi.utils.Response
import com.example.quizzz.domain.QuestionStats
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriviaGetQuizViewModel @Inject constructor(
    auth:FirebaseAuth,
    private val quizAziUseCases: QuizAziUseCases,
    private val userUseCases: UserUseCases,
) : ViewModel() {

    private val userId = auth.currentUser?.uid
    private val _getUserData= mutableStateOf<Response<User?>>(Response.Success(null))
    val getUserData: State<Response<User?>> = _getUserData

    private var _error by mutableStateOf<String?>(null)
    val error: String?
        get() = _error

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _categories = MutableStateFlow<Response<List<CategoryModel>>>(Response.loading())
    val categories: StateFlow<Response<List<CategoryModel>>> = _categories

    private val _questionStats = MutableStateFlow<Response<QuestionStats?>>(Response.loading())
    val questionStats: StateFlow<Response<QuestionStats?>> = _questionStats

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _categories.value = quizAziUseCases.getTopicsUseCase()
                _questionStats.value = quizAziUseCases.getQuestionsStatsUseCase()
                _isLoading.value = true
            } catch (e: Exception) {
                _error = "Failed to fetch categories or question stats: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    fun getUserInfo() {
        if (userId != null) {
            viewModelScope.launch {
                userUseCases.getUserInfo(userId = userId).collect { result ->
                    when (result) {
                        is Response.Success -> {
                            val userData = result.data
                            Log.d("UserInfoTop", "User data: $userData")
                            _getUserData.value = result
                        }
                        is Response.Error -> {
                            Log.e("UserInfoTop", "Error fetching user data: ${result.message}")
                        }

                        else -> {}
                    }
                }
            }
        }
    }

}

