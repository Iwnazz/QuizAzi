package com.example.quizzazi.di

import com.example.quizzazi.data.RepositoryAuthImpl
import com.example.quizzazi.data.TriviaRepositoryImpl
import com.example.quizzazi.data.UserRepositoryImpl
import com.example.quizzazi.data.local.QuizService
import com.example.quizzazi.domain.repository.RepositoryAuth
import com.example.quizzazi.domain.repository.TriviaRepository
import com.example.quizzazi.domain.repository.UserRepository
import com.example.quizzazi.domain.use_cases.auth.AuthenticationUseCases

import com.example.quizzazi.domain.use_cases.auth.FirebaseSignIn
import com.example.quizzazi.domain.use_cases.auth.FirebaseSignOut
import com.example.quizzazi.domain.use_cases.auth.FirebaseSignUp
import com.example.quizzazi.domain.use_cases.quiz.GetQuestionsStatsUseCase
import com.example.quizzazi.domain.use_cases.auth.IsUserAuthenticated
import com.example.quizzazi.domain.use_cases.quiz.GetQuizUseCase
import com.example.quizzazi.domain.use_cases.quiz.GetTopicsUseCase
import com.example.quizzazi.domain.use_cases.quiz.QuizAziUseCases
import com.example.quizzazi.domain.use_cases.user.GetUserInfoUseCase
import com.example.quizzazi.domain.use_cases.user.SetProfileImage
import com.example.quizzazi.domain.use_cases.user.UpdateScoresUseCase
import com.example.quizzazi.domain.use_cases.user.UpdateProfileUseCase
import com.example.quizzazi.domain.use_cases.user.UserUseCases
import com.example.quizzazi.presentation.ui.screens.result.ResultViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object QuizAziModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth: FirebaseAuth, firestore: FirebaseFirestore):RepositoryAuth{
        return RepositoryAuthImpl(auth = auth, fireStore = firestore)
    }

    @Singleton
    @Provides
    fun provideUserRepository(firestore: FirebaseFirestore):UserRepository{
        return UserRepositoryImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideUserUseCases(userRepository: UserRepository)= UserUseCases(
        GetUserInfoUseCase(userRepository),
        SetProfileImage(userRepository),
        UpdateProfileUseCase(userRepository),
        UpdateScoresUseCase(userRepository)
    )

    @Singleton
    @Provides
    fun provideResultViewModel(auth: FirebaseAuth,userUseCases: UserUseCases): ResultViewModel {
        return ResultViewModel(auth, userUseCases)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repositoryAuth: RepositoryAuth)= AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repositoryAuth = repositoryAuth),
        firebaseSignOut = FirebaseSignOut(repositoryAuth = repositoryAuth),
        firebaseSignIn = FirebaseSignIn(repositoryAuth = repositoryAuth),
        firebaseSignUp = FirebaseSignUp(repositoryAuth = repositoryAuth)
    )

    @Provides
    @Singleton
    fun provideTriviaRepository(triviaApi: QuizService): TriviaRepository {
        return TriviaRepositoryImpl(triviaApi)
    }

    @Provides
    @Singleton
    fun provideTriviaApi(): QuizService {
        return Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuizAziUseCases(triviaRepository: TriviaRepository) = QuizAziUseCases(
        getTopicsUseCase = GetTopicsUseCase(triviaRepository),
        getQuestionsStatsUseCase = GetQuestionsStatsUseCase(triviaRepository),
        getQuizUseCase = GetQuizUseCase(triviaRepository)
    )

}