package com.example.quizazi.presentation.screens.board

import androidx.annotation.DrawableRes
import com.example.newsapp.utils.Constance.title
import com.example.newsapp.utils.Constance.title1
import com.example.newsapp.utils.Constance.title2
import com.example.quizzazi.R

data class Page(
    val title : String,
    @DrawableRes val image : Int
)

val pages = listOf(
    Page(title = title, image = R.drawable.img),
    Page(title = title1,  image = R.drawable.img_1),
    Page(title = title2,  image = R.drawable.img_2)
)