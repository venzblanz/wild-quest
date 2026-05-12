package com.android.finalproject.data.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "quiz_attempts",
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
data class QuizAttemptEntity(
    @PrimaryKey(autoGenerate = true)
    val attemptId: Int = 0,

    val userId: Int,

    val score: Int,
    val totalScore: Int,

    val mode: String,        // example: "solo_competitive", "group_competitive"
    val category: String? = null,

    val isOfficial: Boolean = true,

    val dateTaken: Long = System.currentTimeMillis()
)