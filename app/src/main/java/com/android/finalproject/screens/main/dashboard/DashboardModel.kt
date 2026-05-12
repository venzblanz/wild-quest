package com.android.finalproject.screens.main.dashboard

import android.content.Context
import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.data.database.WildQuestDatabase
import com.android.finalproject.data.users.Users

class DashboardModel(private val app: WildQuestApp, private val context: Context) {
    private val db = WildQuestDatabase.getDatabase(context)
    suspend fun getFullName(username: String): String{
        return db.userDao().getUserByUsername(username)?.fullName ?: "Unknown Adventurer"
    }
    fun getUser(): Users = app.getUser()
}