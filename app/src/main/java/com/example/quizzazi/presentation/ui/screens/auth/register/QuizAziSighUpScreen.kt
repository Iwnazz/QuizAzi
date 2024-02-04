package com.example.quizzazi.presentation.ui.screens.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsapp.utils.Constance
import com.example.quizzazi.presentation.ui.nav.Screens
import com.example.quizzazi.presentation.components.register.ClickableLoginText
import com.example.quizzazi.presentation.components.register.RegisterEditTexts
import com.example.quizzazi.utils.getBlackToWhiteColor

@Composable
fun QuizAziSignUpScreen(navController: NavHostController, viewModel: RegisterScreenViewModel= hiltViewModel()){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(getBlackToWhiteColor()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
       RegisterEditTexts(navController, viewModel)
        Spacer(modifier = Modifier.height(Constance.MediumTwentyFourDp))
        ClickableLoginText {
            navController.navigate(Screens.QuizAziLoginScreen.route){
                popUpTo(Screens.QuizAziSignUpScreen.route){
                    inclusive = true
                }
            }
        }
    }
}