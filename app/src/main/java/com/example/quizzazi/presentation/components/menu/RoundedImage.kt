package com.example.quizzazi.presentation.components.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.quizzazi.R
import com.example.quizzazi.utils.getDarkToLightColor


@Composable
fun RoundedImage(imageUrl: String?, userName: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(CircleShape)
    ) {
        if (imageUrl.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .background(getDarkToLightColor(), CircleShape)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = userName.take(1),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            val imagePainter = rememberAsyncImagePainter(model = imageUrl)
            Image(
                painter = imagePainter,
                contentDescription = "imageUser",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}