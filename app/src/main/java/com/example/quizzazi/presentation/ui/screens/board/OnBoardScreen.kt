package com.example.quizazi.presentation.screens.board

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.newsapp.utils.Constance.MediumTwentyFourDp
import com.example.quizazi.presentation.components.board.OnBoardBackButton
import com.example.quizazi.presentation.components.board.OnBoardForwardButton
import com.example.quizazi.presentation.components.board.OnBoardTop
import com.example.quizzazi.presentation.ui.nav.Screens
import com.example.quizzazi.utils.getBlackToWhiteColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardScreen(navController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize().background(getBlackToWhiteColor())){
        val pagerState = rememberPagerState(initialPage = 0){
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage){
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("","")
                }
            }
        }
        HorizontalPager(state = pagerState) {
            OnBoardTop(page = pages[it], pagerState = pagerState.currentPage)
        }
            Spacer(modifier = Modifier.weight(1f))
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumTwentyFourDp)
            .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ){

                val scope = rememberCoroutineScope()
            if (buttonState.value[0].isNotEmpty()){
                OnBoardBackButton(text = buttonState.value[0],
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    }
                )
            }
            OnBoardForwardButton(text = buttonState.value[1], onClick = {
                scope.launch {
                    if (pagerState.currentPage == 2){
                        navController.navigate(Screens.QuizAziLoginScreen.route){
                            popUpTo(Screens.OnBoardScreen.route) {
                                inclusive = true
                            }
                        }
                    }else{
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1
                        )
                    }
                }
            })
        }
        Spacer(modifier = Modifier.weight(0.2f))
    }
}