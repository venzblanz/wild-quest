package com.android.finalproject.screens.main.groupreview

interface GroupReviewContract {
    interface View {
        fun setProfileID(id: String)
        fun navigateToCompetitiveRoom()
        fun navigateToCustomRoom()
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
        fun navToCustRoom()
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