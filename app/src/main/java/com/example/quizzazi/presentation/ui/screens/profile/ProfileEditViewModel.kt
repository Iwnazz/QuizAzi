package com.example.quizzazi.presentation.ui.screens.profile

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzazi.domain.use_cases.user.UserUseCases
import com.example.quizzazi.utils.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val useCases: UserUseCases
    ): ViewModel(){

    private val userId = auth.currentUser?.uid
    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri: StateFlow<Uri?> = _imageUri

    private val _setProfileImage = mutableStateOf(Response.success(false))
    val setProfileImage: State<Response<Boolean>> = _setProfileImage

    private val _updateImageUrl = mutableStateOf(Response.success(false))
    val updateImageUrl: State<Response<Boolean>> = _updateImageUrl


    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    fun setImageUri(uri: Uri?) {
        _imageUri.value = uri
        Log.d("ProfileEditViewModel", "Image URI updated: $uri")
    }

    fun setName(newName: String) {
        _name.value = newName
    }

    fun updateProfile() {
        if (userId != null) {
            viewModelScope.launch {
                useCases.updateProfileUseCase(userId = userId, _name.value, _imageUri.value).collect { result ->
                    when (result) {
                        is Response.Success -> {
                            val userData = result.data
                            Log.d("UserInfoTop", "User data: $userData")
                            _updateImageUrl.value = result
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