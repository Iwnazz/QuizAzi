package com.example.quizzazi.presentation.ui.screens.auth.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzazi.domain.use_cases.auth.AuthenticationUseCases
import com.example.quizzazi.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {

    private val _registrationState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val registrationState: State<Response<Boolean>> = _registrationState

    fun signUp(email: String, password: String, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                authenticationUseCases.firebaseSignUp(email = email, password = password, userName = username)
                    .collect {
                        _registrationState.value = it
                    }
            } catch (e: Exception) {
                // Обработка ошибок регистрации
                _registrationState.value = Response.Error("Registration failed: ${e.localizedMessage ?: "Unknown error"}")
            }
        }
    }
}