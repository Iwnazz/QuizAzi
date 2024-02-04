package com.example.quizzazi.presentation.components.result

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quizzazi.R
import com.example.quizzazi.presentation.theme.BeautifulGreen
import com.example.quizzazi.presentation.theme.BeautifulRed
import com.example.quizzazi.presentation.theme.MyYellow

@Composable
fun CorrectOrNot(userAnswer: String?, correctAnswer: Int) {
    if (correctAnswer == 1){
        Text(text = userAnswer ?: "Not answered", color = MyYellow,
            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,)
    }else{
        userAnswer?.let { Text(text = it, color = BeautifulRed,
            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,) }
    }
}
