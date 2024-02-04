package com.example.quizzazi.presentation.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzazi.R
import com.example.quizzazi.presentation.theme.MyYellow
import com.example.quizzazi.utils.getBlackToWhiteColor
import com.example.quizzazi.utils.getDarkToLightColor

@Composable
fun PlayerStatItem(icon: Int, label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = getDarkToLightColor()
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 24.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 26.dp)
                ,
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier,
                ) {
                    Text(
                        text = label,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = MyYellow
                    )
                    Text(
                        text = value,
                        fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = getBlackToWhiteColor(),
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

