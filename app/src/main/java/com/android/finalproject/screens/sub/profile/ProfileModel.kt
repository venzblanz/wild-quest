package com.android.finalproject.screens.sub.profile

import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.data.users.Users

class ProfileModel (private val app: WildQuestApp) {
    fun getUser(): Users = app.getUser()
}