package com.example.quizzazi.domain.use_cases.user

import com.example.quizzazi.domain.repository.UserRepository
import com.google.android.material.imageview.ShapeableImageView
import javax.inject.Inject

class SetProfileImage @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(imageRef: String?, view: ShapeableImageView)
    = userRepository.setProfileImage(imageRef, view)
}