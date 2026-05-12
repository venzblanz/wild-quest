package com.android.finalproject.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.finalproject.data.tables.QuestionEntity

@Dao
interface QuestionDAO {

    @Insert
    suspend fun insertQuestion(question: QuestionEntity): Long

    @Insert
    suspend fun insertQuestions(questions: List<QuestionEntity>)

    @Update
    suspend fun updateQuestion(question: QuestionEntity)

    @Delete
    suspend fun deleteQuestion(question: QuestionEntity)

    @Query("SELECT * FROM questions")
    suspend fun getAllQuestions(): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE questionId = :questionId LIMIT 1")
    suspend fun getQuestionById(questionId: Long): QuestionEntity?

    @Query("SELECT * FROM questions WHERE category = :category")
    suspend fun getQuestionsByCategory(category: String): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE type = :type")
    suspend fun getQuestionsByType(type: String): List<QuestionEntity>

    @Query("SELECT * FROM questions ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestions(limit: Int): List<QuestionEntity>

    @Query("""
        SELECT * FROM questions 
        WHERE category = :category 
        ORDER BY RANDOM() 
        LIMIT :limit
    """)
    suspend fun getRandomQuestionsByCategory(
        category: String,
        limit: Int
    ): List<QuestionEntity>

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun getQuestionCount(): Int

    @Query("DELETE FROM questions")
    suspend fun deleteAllQuestions()
}