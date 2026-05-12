package com.android.finalproject.screens.main.register

import android.content.Context
import com.android.finalproject.data.database.WildQuestDatabase
import com.android.finalproject.data.tables.UserEntity
import com.android.finalproject.data.users.UserStore
import com.android.finalproject.data.users.Users

class RegisterModel (private val context: Context){

    private val db = WildQuestDatabase.getDatabase(context)
    suspend fun register(username: String, password: String, email: String): Boolean {
        return try {
            val newId = db.userDao().registerUser(
                UserEntity(
                    username = username,
                    email = email,
                    password = password
                )
            )
            val insertedUser = db.userDao().getUserById(newId.toInt())
            if(insertedUser != null){
                db.userDao().updateUser(
                    insertedUser.copy(
                        fullName = "Wild Adventurer $newId"
                    )
                )
            }
            true
        } catch (e: Exception){
            false
        }
    }
    fun get(username: String, password: String, email: String): Boolean {
        val user = Users(username, password, email)
        return UserStore.find(user)
    }
}