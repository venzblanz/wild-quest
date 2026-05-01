package com.android.finalproject.screens.main.register

import com.android.finalproject.data.users.UserStore
import com.android.finalproject.data.users.Users

class RegisterModel {
    fun register(username: String, password: String, email: String): Boolean {
        val user = Users(username, password, email)
        return UserStore.register(user)
    }
    fun get(username: String, password: String, email: String): Boolean {
        val user = Users(username, password, email)
        return UserStore.find(user)
    }
}