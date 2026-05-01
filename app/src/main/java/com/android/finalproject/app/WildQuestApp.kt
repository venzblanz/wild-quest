package com.android.finalproject.app

import android.app.Application
import android.util.Log
import com.android.finalproject.data.users.Users

class WildQuestApp : Application() {
    private var userInfo = Users()
    override fun onCreate() {
        super.onCreate()
        Log.e("WildQuestApp","WildQuestApp:onCreate() is called")
    }
    fun getUser() = this.userInfo

    fun setUser(users: Users){
        this.userInfo = users
    }
//    fun appendSubject(subject: Subject){
//        this.userInfo.subjects.add(subject)
//    }
//    fun removeSubject(index: Int){
//        this.userInfo.subjects.removeAt(index)
//    }
}