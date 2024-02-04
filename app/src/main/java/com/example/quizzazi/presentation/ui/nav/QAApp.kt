package com.example.quizzazi.presentation.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quizazi.presentation.screens.auth.QuizAziLoginScreen
import com.example.quizazi.presentation.screens.board.OnBoardScreen
import com.example.quizzazi.presentation.ui.screens.auth.ForgotPasswordScreen
import com.example.quizzazi.presentation.ui.screens.auth.register.QuizAziSignUpScreen
import com.example.quizzazi.presentation.ui.screens.game.TriviaGameScreen
import com.example.quizzazi.presentation.ui.screens.menu.TriviaCategoryScreen
import com.example.quizzazi.presentation.ui.screens.profile.ProfileEditScreen
import com.example.quizzazi.presentation.ui.screens.profile.UserProfileScreen
import com.example.quizzazi.presentation.ui.screens.result.ResultScreen
import com.example.squareapp.present.theme.presenter.ui.splash.SplashScreen

@Composable
fun QAApp(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.TriviaScreen.route) {
            TriviaCategoryScreen(navController)
        }
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = Screens.OnBoardScreen.route) {
            OnBoardScreen(navController)
        }
        composable(route = Screens.QuizAziLoginScreen.route) {
            QuizAziLoginScreen(navController)
        }
        composable(route = Screens.QuizAziSignUpScreen.route) {
            QuizAziSignUpScreen(navController)
        }
        composable(route = Screens.ForgotPasswordScreen.route) {
            ForgotPasswordScreen()
        }
        composable(route = "${Screens.TriviaGameScreen.route}/{categoryId}") { backStackEntry ->
            val categoryIdRoute = backStackEntry.arguments?.getString("categoryId")
            TriviaGameScreen(navController, categoryId = categoryIdRoute!!)
        }
        composable(route = Screens.ResultScreen.route) {
            ResultScreen(navController = navController)
        }
        composable(route = Screens.ProfileScreen.route) {
            UserProfileScreen(navController)
        }
        composable(route= Screens.ProfileEditScreen.route) {
            ProfileEditScreen()
        }
    }

}