package com.android.finalproject.screens.main.dashboard

import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.data.users.Users

class DashboardModel(private val app: WildQuestApp) {
    fun getUser(): Users = app.getUser()
}