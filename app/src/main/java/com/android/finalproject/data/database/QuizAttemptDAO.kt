package com.android.finalproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.finalproject.data.tables.QuizAttemptEntity

@Dao
interface QuizAttemptDAO {

    @Insert
    suspend fun insertAttempt(attempt: QuizAttemptEntity): Long

    @Query("SELECT * FROM quiz_attempts WHERE userId = :userId ORDER BY dateTaken DESC")
    suspend fun getAttemptsByUser(userId: Int): List<QuizAttemptEntity>

    @Query("SELECT * FROM quiz_attempts WHERE userId = :userId AND isOfficial = 1 ORDER BY dateTaken DESC")
    suspend fun getOfficialAttemptsByUser(userId: Int): List<QuizAttemptEntity>

    @Query("SELECT SUM(score) FROM quiz_attempts WHERE userId = :userId AND isOfficial = 1")
    suspend fun getOfficialTotalScore(userId: Int): Int?

    @Query("SELECT SUM(totalScore) FROM quiz_attempts WHERE userId = :userId AND isOfficial = 1")
    suspend fun getOfficialTotalPerfectScore(userId: Int): Int?

    @Query("SELECT COUNT(*) FROM quiz_attempts WHERE userId = :userId AND isOfficial = 1")
    suspend fun getOfficialQuizCount(userId: Int): Int
}