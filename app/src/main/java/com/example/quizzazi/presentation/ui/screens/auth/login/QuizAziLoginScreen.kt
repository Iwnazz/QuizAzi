package com.example.quizazi.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quizazi.presentation.components.login.LoginButtons
import com.example.quizazi.presentation.components.login.LoginTop
import com.example.quizzazi.presentation.ui.screens.auth.login.LoginScreenViewModel
import com.example.quizzazi.presentation.ui.screens.splash.AuthViewModel
import com.example.quizzazi.utils.getBlackToWhiteColor


@Composable
fun QuizAziLoginScreen(navController: NavHostController, viewModel: LoginScreenViewModel= hiltViewModel()){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(getBlackToWhiteColor()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        LoginTop(navController, viewModel)
        LoginButtons(navController)
    }

}