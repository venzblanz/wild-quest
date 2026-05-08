package com.android.finalproject.data.questions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_table")
data class Quiz (

    @PrimaryKey(autoGenerate = true)
    val quiz_id: Int = 0,

    val questions: String,
    val answer: String,
    val score: Int,
    val category: String,
    val type: String
)