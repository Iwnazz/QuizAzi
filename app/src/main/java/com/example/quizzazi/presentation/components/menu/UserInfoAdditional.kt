package com.example.quizzazi.presentation.components.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.utils.phrases
import com.example.quizzazi.R
import java.time.LocalDateTime

@Composable
fun UserInfoAdditional(obj: String, obj1: String?) {

    val greeting = getGreeting()
    val firstPhrase = phrases.random()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = "$greeting, $obj",
                modifier = Modifier,
                fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = firstPhrase.take(25),
                modifier = Modifier,
                fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = firstPhrase
                    .drop(25)
                    .take(23),
                modifier = Modifier,
                fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Rounded Image
       /* RoundedImage(
            imageUrl = obj1.toString(),
            userName = obj.toString(),
            modifier = Modifier.size(80.dp)
        )*/
    }
}

private fun getGreeting(): String {
    return when (LocalDateTime.now().toLocalTime().hour) {
        in 6..11 -> "Good morning"
        in 12..17 -> "Guten Tag"
        in 18..22 -> "Good evening"
        else -> "Can't sleep?"
    }
}
