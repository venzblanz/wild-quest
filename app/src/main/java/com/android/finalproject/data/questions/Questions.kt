package com.android.finalproject.data.questions

data class Questions (
    var question: String,
    var choices: List<String>,
    var correctAnswer: String,
    var type: String = ""
)