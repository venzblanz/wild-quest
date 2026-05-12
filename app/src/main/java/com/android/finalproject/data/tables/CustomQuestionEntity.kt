package com.android.finalproject.data.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "custom_questions",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userId")]
)
data class CustomQuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val customQuestionId: Int = 0,

    val userId: Int,

    val questionText: String,
    val correctAnswer: String,
    val points: Int = 1,
    val category: String,
    val type: String,

    val createdAt: Long = System.currentTimeMillis()
)
