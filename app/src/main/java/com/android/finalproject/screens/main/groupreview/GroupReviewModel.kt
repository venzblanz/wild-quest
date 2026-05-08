package com.android.finalproject.screens.main.groupreview

import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.data.users.Users

class GroupReviewModel (private val app: WildQuestApp) {
    fun getUser(): Users = app.getUser()
}