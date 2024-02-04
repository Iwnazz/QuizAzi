package com.example.quizzazi.domain.use_cases.user

import android.net.Uri
import com.example.quizzazi.domain.repository.UserRepository
import com.example.quizzazi.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(
        userId: String, name:String?, imgUri: Uri?
    ): Flow<Response<Boolean>> {
        return userRepository.updateProfile(userId, name, imgUri)
    }
}