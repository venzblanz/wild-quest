package com.android.finalproject.screens.main.login

import android.content.Context
import com.android.finalproject.data.database.WildQuestDatabase
import com.android.finalproject.data.tables.UserEntity

class LoginModel (private val context: Context){
    private val db = WildQuestDatabase.getDatabase(context)
    suspend fun login(username: String, password: String): UserEntity? {
        return db.userDao().login(username, password)
    }
}