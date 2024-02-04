package com.example.quizzazi.presentation.components.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzazi.R

@Composable
fun QuestionCircleLabel(
    questionNumber: Int = 1,
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(shape = RoundedCornerShape(16.dp), color = Color.White),
    ) {
        Text(
            text = "Q$questionNumber",
            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize()
        )

    }
}
