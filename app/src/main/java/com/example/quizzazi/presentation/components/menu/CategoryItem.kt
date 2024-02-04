package com.example.quizzazi.presentation.components.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizzazi.R
import com.example.quizzazi.domain.model.quiz.CategoryModel
import com.example.quizzazi.presentation.ui.nav.Screens
import com.example.quizzazi.utils.getDarkToLightColor
import com.example.quizzz.domain.CategoryStats

@Composable
fun CategoryItem(category: CategoryModel, categoryStats: CategoryStats?, navController: NavController) {

    Card(
        modifier = Modifier
            .padding(8.dp)
        ,
        RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = getDarkToLightColor()
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 24.dp
        )
    ) {   Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate("${Screens.TriviaGameScreen.route}/${category.id}"){
                    popUpTo(Screens.TriviaScreen.route) {
                        inclusive = true
                    }
                }
            }
            .padding(8.dp),
    ) {

        Image(
            painter = painterResource(id = category.image),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = category.name,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            maxLines = 2
        )

        categoryStats?.let {
            Text(
                text = "Questions: ${it.total_num_of_questions}",
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally),
                fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
    }
}