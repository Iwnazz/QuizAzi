package com.example.quizzazi.domain.repository

import android.net.Uri
import com.example.quizzazi.domain.model.user.User
import com.example.quizzazi.utils.Response
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserInfo(userId: String): Flow<Response<User?>>
    fun updateProfile(userId: String, name: String?, imgUri: Uri?): Flow<Response<Boolean>>
    fun setProfileImage(imageRef: String?, view: ShapeableImageView): Flow<Response<String>>
    fun updateScores(userId: String, newScore: Double): Flow<Response<Boolean>>
}