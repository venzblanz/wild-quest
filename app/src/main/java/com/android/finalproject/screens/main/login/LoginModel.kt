package com.android.finalproject.screens.main.login

import com.android.finalproject.data.users.UserStore

class LoginModel {
    fun login(username: String, password: String): Boolean {
        return UserStore.login(username, password)
    }
}