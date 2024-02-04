@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.quizzazi.domain.model.quiz

import android.os.Parcel
import android.os.Parcelable
import com.example.quizzz.domain.QuizResult

data class ResultModel(
    var time: Long,
    var type: String?,
    var difficulty: String?,
    var score: Double,
    var correctAnswers: Int,
    var incorrectAnswers: Int,
    var userAnswer: String?,
    var question: String?,
    var percentage: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
        parcel.writeString(type)
        parcel.writeString(difficulty)
        parcel.writeDouble(score)
        parcel.writeInt(correctAnswers)
        parcel.writeInt(incorrectAnswers)
        parcel.writeString(userAnswer)
        parcel.writeString(question)
        parcel.writeDouble(percentage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultModel> {
        override fun createFromParcel(parcel: Parcel): ResultModel {
            return ResultModel(parcel)
        }

        override fun newArray(size: Int): Array<ResultModel?> {
            return arrayOfNulls(size)
        }
    }

}

fun QuizResult.toResultModel(elapsedTime: Long): ResultModel {
    val correctAnswers = if (isAnswerCorrect) 1 else 0
    val incorrectAnswers = if (isAnswerCorrect) 0 else 1
    val totalQuestions = 1
    val percentage = (correctAnswers.toDouble() / totalQuestions.toDouble()) * 100.0

    return ResultModel(
        time = elapsedTime,
        type = type,
        difficulty = difficulty,
        score = calculateUserScore(this),
        correctAnswers = correctAnswers,
        incorrectAnswers = incorrectAnswers,
        userAnswer = user_answer,
        question = question,
        percentage = percentage
    )
}
private fun calculateUserScore(result: QuizResult): Double {
    return if (result.isAnswerCorrect) 1.0 else 0.0
}