package com.example.quizazi.presentation.components.board

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsapp.utils.BaseText
import com.example.newsapp.utils.Constance
import com.example.newsapp.utils.Constance.FourDp
import com.example.newsapp.utils.Constance.MediumThirtyDp
import com.example.newsapp.utils.Constance.MediumTwentyFourDp

import com.example.quizazi.presentation.screens.board.Page
import com.example.quizazi.presentation.screens.board.pages

import com.example.quizzazi.presentation.theme.CardViewColor
import com.example.quizzazi.presentation.theme.MyDarkBlueColor
import com.example.quizzazi.presentation.theme.MyPurpleColor
import com.example.quizzazi.utils.getDarkToLightColor


@Composable
fun OnBoardTop(
    modifier: Modifier = Modifier,
    page: Page,
    pagerState: Int
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Изображение с настройками
        Image(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.5f)
                .clip(MaterialTheme.shapes.medium),
            painter = painterResource(id = page.image),
            contentDescription ="page_image",
            contentScale = ContentScale.Inside
        )

        Spacer(modifier = Modifier.height(MediumTwentyFourDp))

        // Индикатор
        OnBoardIndicator(
            modifier = Modifier.width(Constance.FiftyTwoDp),
            pageSize = pages.size,
            selectedPage = pagerState
        )

        Spacer(modifier = Modifier.height(MediumTwentyFourDp))

        // Кардвью с текстом
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MediumTwentyFourDp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(FourDp),
            colors = CardDefaults.cardColors(
                containerColor = CardViewColor
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(MediumThirtyDp)
                    .fillMaxWidth()
            ) {
                // Текст
                BaseText(
                    text = page.title,
                    modifier = Modifier.fillMaxWidth(),
                    color =  getDarkToLightColor()
                )
            }
        }
    }
}
