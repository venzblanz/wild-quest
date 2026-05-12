package com.android.finalproject.data.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val questionId: Int = 0,

    val questionText: String,
    val questionAnswer: String,
    val points: Int,

    val category: String,
    val type: String,

    val optionA: String = "",
    val optionB: String = "",
    val optionC: String = "",
    val optionD: String = ""
)