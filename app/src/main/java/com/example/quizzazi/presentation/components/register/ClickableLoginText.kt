package com.example.quizzazi.presentation.components.register

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.quizzazi.R
import com.example.quizzazi.utils.getDarkToLightColor

@Composable
fun ClickableLoginText(
    onClick: () -> Unit
) {
    Surface {
        ClickableText(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                        fontSize = 22.sp
                    )
                ) {
                    append("Joined us before?")
                }
                withStyle(
                    style = SpanStyle(
                        color = getDarkToLightColor(),
                        fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                                fontSize = 22.sp
                    )
                ) {
                    append(" Login")
                }
            },
            onClick = { offset ->
                if (offset >= "Joined us before?".length) {
                    onClick()
                }
            },
        )
    }
}




