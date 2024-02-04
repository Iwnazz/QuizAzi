package com.example.quizzazi.domain.use_cases.user

data class UserUseCases(
    val getUserInfo: GetUserInfoUseCase,
    val setProfileImage: SetProfileImage,
    val updateProfileUseCase: UpdateProfileUseCase,
    val updateScoresUseCase: UpdateScoresUseCase
)