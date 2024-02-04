package com.example.quizzazi.domain.model.user

data class User(
    var userName : String = "",
    var userid : String = "",
    var email : String = "",
    var password : String = "",
    var imageUrl : String = "",
    val allTimeScore:Double = 0.0,
    val weeklyScore:Double = 0.0,
    val monthlyScore:Double = 0.0,
    val lastGameScore:Double = 0.0
)
