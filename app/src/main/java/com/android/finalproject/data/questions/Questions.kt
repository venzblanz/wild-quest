package com.android.finalproject.data.questions
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Questions (
    var question: String,
    var choices: List<String>,
    var correctAnswer: String,
    var type: String = ""
) : Parcelable