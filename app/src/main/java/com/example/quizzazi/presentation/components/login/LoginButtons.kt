package com.example.quizazi.presentation.components.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newsapp.utils.BaseText
import com.example.newsapp.utils.GradientButton
import com.example.quizzazi.presentation.ui.nav.Screens
import com.example.quizzazi.utils.getDarkToLightColor

@Composable
fun LoginButtons(navController: NavHostController){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
        ) {
            BaseText(
                text = "OR",
                modifier = Modifier.padding(vertical = 12.dp),
                color =  getDarkToLightColor()
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                shape = MaterialTheme.shapes.medium, // Устанавливает форму кнопки
                color = if (isSystemInDarkTheme()) Color.White else Color.Black// Прозрачный цвет фона
            ) {
                GradientButton(
                    textColor = Color.Black,
                    iconUrl = "https://img.icons8.com/color/1x/google-logo.png",
                    modifier = Modifier
                        .fillMaxWidth()
                ) {}
            }

            BaseText(
                text = "Don't have an account?",
                modifier = Modifier.padding(top = 16.dp)
                    .clickable {
                        navController.navigate(Screens.QuizAziSignUpScreen.route) {
                            popUpTo(Screens.QuizAziLoginScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                color =  getDarkToLightColor()
            )
        }
    }

