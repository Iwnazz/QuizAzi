package com.example.quizzazi.presentation.ui.screens.auth


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.quizzazi.presentation.components.forgot.ForgotPasswordChapter

import com.example.quizzazi.utils.getBlackToWhiteColor


@Composable
fun ForgotPasswordScreen(){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(getBlackToWhiteColor()),
        verticalArrangement = Arrangement.Center){
        ForgotPasswordChapter()
    }
}