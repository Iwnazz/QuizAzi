package com.example.quizzazi.presentation.ui.screens.profile

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzazi.domain.model.user.User
import com.example.quizzazi.domain.use_cases.auth.AuthenticationUseCases
import com.example.quizzazi.domain.use_cases.user.UserUseCases
import com.example.quizzazi.utils.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val useCases: UserUseCases,
    private val authenticationUseCases: AuthenticationUseCases
    ): ViewModel() {

    private val userId = firebaseAuth.currentUser?.uid
    private val _getUserData= mutableStateOf<Response<User?>>(Response.Success(null))
    val getUserData: State<Response<User?>> = _getUserData
    private val _userImageUri = MutableStateFlow<Uri?>(null)
    val userImageUri: StateFlow<Uri?> = _userImageUri

    // Добавьте метод для обновления Uri изображения
    fun updateUserImageUri(uri: Uri?) {
        _userImageUri.value = uri
    }

    private val _signInState = mutableStateOf(Response.success(false))

    private val _signOutState = mutableStateOf(Response.success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    fun getUserInfo() {
        if (userId != null) {
            viewModelScope.launch {
                useCases.getUserInfo(userId = userId).collect { result ->
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

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            _signOutState.value = Response.loading()
            authenticationUseCases.firebaseSignOut().collectLatest { result ->
                _signOutState.value = result
                if (result is Response.Success && result.data) {
                    _signInState.value = Response.success(false)
                }
            }
        }
    }
}