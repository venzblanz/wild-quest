package com.android.finalproject.data.questions

object QuestionStore {
    val questions = listOf(
        Questions(
            question = "What are the 11 numbers after pi?",
            choices = listOf("3.1415926535897", "3.1415922535897", "3.1415956535897", "3.1415926535891"),
            correctAnswer = "3.1415926535897",
            type = "multiple_choice"
        ),
        Questions(
            question = "Which keyword is used to create a class in Kotlin?",
            choices = listOf("function", "class", "object", "new"),
            correctAnswer = "class",
            type = "multiple_choice"
        ),
        Questions(
            question = "Who is the father of Computer",
            choices = emptyList(),
            correctAnswer = "Charles Babbage",
            type = "identification"
        )
    )
}