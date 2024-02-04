package com.example.quizzazi.presentation.components.menu

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.utils.getGreeting
import com.example.newsapp.utils.phrases
import com.example.quizzazi.R
import com.example.quizzazi.presentation.components.game.DisplayLoadingIndicator
import com.example.quizzazi.presentation.components.profile.PlayerStatItem
import com.example.quizzazi.presentation.ui.screens.menu.TriviaGetQuizViewModel
import com.example.quizzazi.utils.Response


@SuppressLint("SuspiciousIndentation")
@Composable
fun UserInfoTop(triviaGetQuizViewModel: TriviaGetQuizViewModel) {
    triviaGetQuizViewModel.getUserInfo()

    when (val response = triviaGetQuizViewModel.getUserData.value) {
        is Response.Loading -> {
            DisplayLoadingIndicator()
        }
        is Response.Success -> {
            Log.d("UserInfoTop", "Successfully loaded data: ${response.data}")
            if (response.data != null) {
                val obj = response.data
                val greeting = getGreeting()
                val firstPhrase = phrases.random()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.padding(top = 8.dp)) {
                                Text(
                                    text = "$greeting, ${obj.userName}",
                                    modifier = Modifier,
                                    fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = firstPhrase.take(25),
                                    modifier = Modifier,
                                    fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = firstPhrase
                                        .drop(25)
                                        .take(23),
                                    modifier = Modifier,
                                    fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            RoundedImage(
                                imageUrl = obj.imageUrl,
                                userName = obj.userName,
                                modifier = Modifier.size(80.dp)
                            )
                        }
                        PlayerStatItem(
                            icon = R.drawable.star,
                            label = "AziPoints",
                            value = obj.allTimeScore.toString()
                        )
                    }
            }
        }
        is Response.Error -> {
            Text(text = "Error")
        }
    }
}

