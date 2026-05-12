package com.android.finalproject.screens.sub.profile

import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.data.database.WildQuestDatabase
import com.android.finalproject.data.users.Users

class ProfileModel(private val app: WildQuestApp) {
    fun getUser(): Users = app.getUser()

    private val db = WildQuestDatabase.getDatabase(app)
    suspend fun getQuizCount(userId: Int): Int {
        return db.quizAttemptDao().getOfficialQuizCount(userId)
    }

    suspend fun getTotalScore(userId: Int): Int {
        return db.quizAttemptDao().getOfficialTotalScore(userId) ?: 0
    }

    suspend fun getTotalPerfectScore(userId: Int): Int {
        return db.quizAttemptDao().getOfficialTotalPerfectScore(userId) ?: 0
    }

    suspend fun getBestScore(userId: Int): Int {
        val attempts = db.quizAttemptDao().getOfficialAttemptsByUser(userId)
        return attempts.maxOfOrNull { it.score } ?: 0
    }
    suspend fun getAccuracy(userId: Int): String {
        val earned  = getTotalScore(userId)
        val perfect = getTotalPerfectScore(userId)
        if (perfect == 0) return "0%"
        val percent = (earned.toDouble() / perfect.toDouble() * 100).toInt()
        return "$percent%"
    }
}