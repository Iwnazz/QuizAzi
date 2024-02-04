package com.example.quizzazi.presentation.components.game

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzazi.R
import com.example.quizzazi.presentation.theme.BeautifulGreen
import com.example.quizzazi.presentation.theme.BeautifulRed
import com.example.quizzazi.presentation.theme.Secondary
import com.example.quizzazi.utils.getDarkToLightColor


@Composable
fun AnswerOptionButton(
    answer: String,
    selected: Boolean,
    correct: Boolean,
    onAnswerSelected: (String) -> Unit
) {
    val buttonContainerColor by animateColorAsState(
        when {
            selected && correct -> BeautifulGreen
            selected && !correct -> BeautifulRed
            else -> getDarkToLightColor()
        },
        tween(300), label = ""
    )
    val buttonBorderColor by animateColorAsState(
        if (selected) Secondary else Color.Transparent,
        tween(300), label = ""
    )

    Button(
        onClick = { onAnswerSelected(answer) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .wrapContentHeight()
            .defaultMinSize(minHeight = 54.dp)
            .border(1.dp, buttonBorderColor, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(buttonContainerColor, disabledContainerColor = buttonContainerColor),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = answer,
            color = Black,
            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
            fontWeight = FontWeight.Bold,
        )
    }
}