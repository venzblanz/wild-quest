package com.android.finalproject.screens.main.soloreview.lobby

interface SoloReviewLobbyContract {
    interface View {
        fun setProfileID(id: String)
        fun navigateToCompetitiveRoom()
        fun navigateToGroupRev()
        fun navigateToHome()
        fun navigateToLeaderboards()
        fun navigateToGroups()
        fun navigateToProfile()
        fun navigateToHistory()
        fun navigateToSettings()
        fun navigateToFAQ()
        fun navigateToLogo()
        fun showSuccessLogoutMsg()
    }
    interface Presenter {
        fun setProfileID()
        fun navToCompeRoom()
        fun navToGroupRev()
        fun navToHome()
        fun navToLeaderboards()
        fun navToGroups()
        fun navToProfile()
        fun navToHistory()
        fun navToSettings()
        fun logout()
        fun navToFAQ()
    }
}