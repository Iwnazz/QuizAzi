package com.example.quizzazi.presentation.components.forgot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.utils.BaseText
import com.example.newsapp.utils.Constance
import com.example.newsapp.utils.DemoField
import com.example.quizzazi.R
import com.example.quizzazi.utils.getDarkToLightColor

@Composable
fun ForgotPasswordChapter() {

    val passwordState = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.img_5),
            contentDescription = "page_image",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        )
        Spacer(modifier = Modifier.height(Constance.MediumTwentyFourDp))
        BaseText(
            text = "Forgot " +
                    "Password?",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Constance.TwentyDp),
            color = getDarkToLightColor()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DemoField(
                values = passwordState.value,
                label = "Email",
                placeholder = "Enter your email",
                onValueChange = { passwordState.value = it },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email")
                }
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = getDarkToLightColor()
                ),
                onClick = {
                    // Действие по нажатию на кнопку
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)

            ) {
                Text(
                    text = "Submit",
                    fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
            }
        }
    }
}
