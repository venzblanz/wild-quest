package com.android.finalproject.data.users

object UserStore {
    private val users = mutableMapOf<String, Users>()

    fun register(user: Users): Boolean {
        if (users.containsKey(user.username)) return false
        users[user.username] = user
        return true
    }
    fun find(user: Users): Boolean {
        if (users.containsKey(user.username)) return false
        return true
    }
    fun getUser(username: String): Users? {
        return users[username]
    }
    fun login(username: String, password: String): Boolean {
        return users[username]?.password == password
    }
}