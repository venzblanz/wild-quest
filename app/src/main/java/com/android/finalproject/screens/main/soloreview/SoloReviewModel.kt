package com.android.finalproject.screens.main.soloreview

import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.data.users.Users

class SoloReviewModel (private val app: WildQuestApp) {
    fun getUser(): Users = app.getUser()
}