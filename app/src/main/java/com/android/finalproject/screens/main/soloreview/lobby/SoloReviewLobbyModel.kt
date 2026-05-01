package com.android.finalproject.screens.main.soloreview.lobby

import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.data.users.Users

class SoloReviewLobbyModel (private val app: WildQuestApp) {
    fun getUser(): Users = app.getUser()
}