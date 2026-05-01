package com.android.finalproject.data.questions

data class AnsweredQuestion(
    val question: Questions,
    val selectedAnswer: String,
    val isCorrect: Boolean
)
