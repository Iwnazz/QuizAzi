package com.example.quizazi.presentation.components.board

import androidx.compose.runtime.Composable
import com.example.newsapp.utils.CustomButton

@Composable
fun OnBoardForwardButton(text: String, onClick: () -> Unit) {
    CustomButton(text = text, onClick = onClick)
}

@Composable
fun OnBoardBackButton(text: String, onClick: () -> Unit) {
    CustomButton(text = text, onClick = onClick)
}