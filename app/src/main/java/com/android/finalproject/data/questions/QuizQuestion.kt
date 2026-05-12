package com.android.finalproject.data.questions

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizQuestion(
    val id: Int = 0,
    val questionText: String,
    val questionAnswer: String,
    val points: Int,
    val category: String,
    val type: String,
    val optionA: String = "",
    val optionB: String = "",
    val optionC: String = "",
    val optionD: String = ""
) : Parcelable
