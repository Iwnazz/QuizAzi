package com.example.squareapp.present.theme.presenter.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quizzazi.R
import com.example.quizzazi.presentation.ui.screens.splash.AuthViewModel
import com.example.quizzazi.presentation.ui.nav.Screens
import com.example.quizzazi.utils.getBlackToWhiteColor
import com.example.quizzazi.utils.getDarkToLightColor
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController, authViewModel: AuthViewModel= hiltViewModel()) {
    val authValue = authViewModel.isUserAuthenticated
    val circleColor =  getDarkToLightColor()
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(durationMillis = 1500, easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            })
        )
        delay(1000)
            if (authValue) {
                navController.navigate(Screens.TriviaScreen.route) {
                    popUpTo(Screens.SplashScreen.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(Screens.OnBoardScreen.route) {
                    popUpTo(Screens.SplashScreen.route) {
                        inclusive = true
                    }
                }
        }
    }

    val background = getBlackToWhiteColor()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Box(
            modifier = Modifier
                .scale(scale.value)
                .background(circleColor, shape = CircleShape)
                .size(340.dp)
        ) {
            val imageResource =
                if (isSystemInDarkTheme()) R.drawable.quiz_dark else R.drawable.quiz
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Splash Screen Logo",
                modifier = Modifier
                    .scale(scale.value)
                    .size(410.dp)
            )
        }
    }
}
