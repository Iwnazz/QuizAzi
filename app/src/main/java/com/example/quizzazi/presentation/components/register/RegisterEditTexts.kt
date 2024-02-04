package com.example.quizzazi.presentation.components.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import com.example.newsapp.utils.Constance
import com.example.newsapp.utils.Constance.SixTeenDp
import com.example.newsapp.utils.DemoField
import com.example.newsapp.utils.Toast
import com.example.quizzazi.R
import com.example.quizzazi.presentation.components.additional.CircularProgressBar
import com.example.quizzazi.presentation.ui.screens.auth.register.RegisterScreenViewModel
import com.example.quizzazi.utils.Response
import com.example.quizzazi.presentation.ui.nav.Screens
import com.example.quizzazi.utils.getDarkToLightColor


@Composable
fun RegisterEditTexts(navController: NavHostController, viewModel: RegisterScreenViewModel) {

    val userNameState = remember {
        mutableStateOf("")
    }
    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.img_4),
            contentDescription = "page_image",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        )
        Spacer(modifier = Modifier.height(Constance.MediumTwentyFourDp))
        Text(
            text = "Sign Up",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Constance.TwentyDp), // Отступ слева
            color = getDarkToLightColor(),
            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SixTeenDp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DemoField(
                values = userNameState.value,
                label = "User name",
                placeholder = "Enter your name",
                onValueChange = { userNameState.value = it },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "Name")
                },
            )
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
            Spacer(modifier = Modifier.height(Constance.MediumTwentyFourDp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = getDarkToLightColor()
                ),
                onClick = {
                    viewModel.signUp(
                        email = emailState.value,
                        password = passwordState.value,
                        username = userNameState.value
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Constance.FourtyDp)

            ) {
                val signUpState = viewModel.registrationState.value
                if (signUpState is Response.Loading) {
                    CircularProgressBar(percentage = 1f, number = 100)
                } else {
                        Text(
                            text = "Sign Up",
                            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp
                        )
                }
                when (signUpState) {
                    is Response.Success -> {
                        if (signUpState.data) {
                            LaunchedEffect(key1 = true) {
                                navController.navigate(Screens.TriviaScreen.route) {
                                    popUpTo(Screens.QuizAziSignUpScreen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }

                    is Response.Error -> {
                        Toast(message = signUpState.message)
                    }

                    else -> {
                        Toast(message = "huy")}
                }
            }
        }
    }
}