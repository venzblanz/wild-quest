package com.android.finalproject.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.finalproject.data.tables.CustomQuestionEntity

@Dao
interface CustomQuestionDAO {
    @Insert
    suspend fun insertCustomQuestion(question: CustomQuestionEntity): Long

    @Update
    suspend fun updateCustomQuestion(question: CustomQuestionEntity)

    @Delete
    suspend fun deleteCustomQuestion(question: CustomQuestionEntity)

    @Query("SELECT * FROM custom_questions WHERE userId = :userId ORDER BY createdAt DESC")
    suspend fun getCustomQuestionsByUser(userId: Int): List<CustomQuestionEntity>

    @Query("""
        SELECT * FROM custom_questions 
        WHERE userId = :userId 
        ORDER BY RANDOM() 
        LIMIT :limit
    """)
    suspend fun getRandomCustomQuestionsByUser(
        userId: Int,
        limit: Int
    ): List<CustomQuestionEntity>
}