package com.example.quizzazi.presentation.ui.nav

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object OnBoardScreen : Screens("on_board")
    object QuizAziLoginScreen : Screens("quiz_azi_login_screen")
    object QuizAziSignUpScreen: Screens("quiz_azi_sign_up_screen")
    object ForgotPasswordScreen: Screens("frg_screen")
    object TriviaScreen : Screens("trivia_screen")
    object ProfileScreen : Screens("profile_screen")
    object TriviaGameScreen : Screens("trivia_game_screen")
    object ResultScreen: Screens("result_screen")
    object ProfileEditScreen : Screens("profile_edit_screen")
    object LeaderBoardScreen: Screens("leader_board_screen")
}