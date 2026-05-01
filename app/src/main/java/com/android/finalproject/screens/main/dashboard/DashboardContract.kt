package com.android.finalproject.screens.main.dashboard

interface DashboardContract {
    interface View {
        fun setProfileID(id: String)
        fun navigateToSoloRev()
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
        fun navToSoloRev()
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