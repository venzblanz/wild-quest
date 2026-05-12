package com.android.finalproject.data.questions

data class AnsweredQuestion(
    val question: QuizQuestion,
    val selectedAnswer: String,
    val isCorrect: Boolean
)