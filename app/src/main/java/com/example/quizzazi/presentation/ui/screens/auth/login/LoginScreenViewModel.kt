package com.example.quizzazi.presentation.ui.screens.auth.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzazi.domain.use_cases.auth.AuthenticationUseCases
import com.example.quizzazi.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {

    private val _signInState = mutableStateOf(Response.success(false))
    val signInState: State<Response<Boolean>> = _signInState

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _signInState.value = Response.loading()
            authenticationUseCases.firebaseSignIn(email = email, password = password).collectLatest { result ->
                _signInState.value = result
            }
        }
    }
}