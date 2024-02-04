package com.example.quizzazi.presentation.components.result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizzazi.utils.getDarkToLightColor

@Composable
fun AnimatedPieChart(
    correctPercentage: Int,
    incorrectPercentage: Int
) {
    var animationPlayed by remember { mutableStateOf(false) }
    LaunchedEffect(animationPlayed) {
        if (!animationPlayed) {
            animationPlayed = true
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = getDarkToLightColor()
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
        ) {
            PieChart(
                data = mapOf(
                    Pair("Correct", correctPercentage),
                    Pair("Incorrect", incorrectPercentage),
                ),
                animationPlayed = animationPlayed,
            )
        }
    }
}

