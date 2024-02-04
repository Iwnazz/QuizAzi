package com.example.quizzazi.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.quizzazi.presentation.theme.MyDarkBlueColor
import com.example.quizzazi.presentation.theme.MyPurpleColor

@Composable
fun getDarkToLightColor(): Color {
    return if (isSystemInDarkTheme()) MyDarkBlueColor else MyPurpleColor
}

@Composable
fun getBlackToWhiteColor(): Color {
    return if (isSystemInDarkTheme()) Color.Black else Color.White
}


