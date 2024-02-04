package com.example.quizzazi.presentation.components.game

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.quizzazi.presentation.ui.screens.game.TriviaGameViewModel
import com.example.quizzazi.presentation.ui.nav.Screens

@Composable
fun DisplayFinishQuizButton(navController: NavHostController, viewModel: TriviaGameViewModel) {
    Button(
        onClick = {
            viewModel.calculateAndSetResults()
            navController.navigate(
                route = Screens.ResultScreen.route,
                navOptions = NavOptions.Builder().setPopUpTo(Screens.TriviaGameScreen.route, inclusive = true).build()
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Finish Quiz")
    }
}