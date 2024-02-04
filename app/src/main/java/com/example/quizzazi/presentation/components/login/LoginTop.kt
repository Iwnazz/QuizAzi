package com.example.quizazi.presentation.components.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.utils.BaseText
import com.example.newsapp.utils.Constance
import com.example.newsapp.utils.Constance.TwentyDp
import com.example.newsapp.utils.DemoField
import com.example.newsapp.utils.Toast
import com.example.quizzazi.R
import com.example.quizzazi.presentation.components.additional.CircularProgressBar
import com.example.quizzazi.presentation.ui.nav.Screens
import com.example.quizzazi.presentation.ui.screens.auth.login.LoginScreenViewModel
import com.example.quizzazi.utils.Response
import com.example.quizzazi.utils.getDarkToLightColor

@Composable
fun LoginTop(navController: NavHostController, viewModel:LoginScreenViewModel) {

    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.img_3),
            contentDescription = "page_image",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        )
        Spacer(modifier = Modifier.height(Constance.MediumTwentyFourDp))
        BaseText(
            text = "Login",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = TwentyDp),
            color = getDarkToLightColor()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DemoField(
                values = emailState.value,
                label = "Email",
                placeholder = "Enter your email",
                onValueChange = { emailState.value = it },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email")
                }
            )
            DemoField(
                values = passwordState.value,
                label = "Password",
                placeholder = "Enter your password",
                onValueChange = { passwordState.value = it },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Password")
                },
            )
        }
        Spacer(modifier = Modifier.height(Constance.TwelveDp))
        BaseText(
            text = "Forgot Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = TwentyDp)
                .wrapContentWidth(Alignment.End)
                .clickable {
                    navController.navigate(Screens.ForgotPasswordScreen.route) {
                        popUpTo(Screens.QuizAziLoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
            color = getDarkToLightColor()
        )
        Spacer(modifier = Modifier.height(Constance.MediumTwentyFourDp))
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = getDarkToLightColor()
            ),
            onClick = {
                viewModel.signIn(
                    emailState.value, passwordState.value
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)

        ) {
            val signInState = viewModel.signInState.value
            if (signInState is Response.Loading) {
                CircularProgressBar(1f, 100)
            } else {
                Text(
                    text = "Login",
                    fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
            }
            when (signInState) {
                is Response.Success -> {
                    LaunchedEffect(key1 = true) {
                        if (signInState.data) {
                            navController.navigate(Screens.TriviaScreen.route) {
                                popUpTo(Screens.QuizAziLoginScreen.route) {
                                    inclusive = true
                                }

                            }
                        }
                    }
                }

                is Response.Error -> {
                    Toast(message = signInState.message)
                }

                else -> {
                    Text(text = " ")
                }

            }
        }
    }
}