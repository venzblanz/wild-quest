package com.android.finalproject.screens.main.dashboard

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.launch

class DashboardPresenter (private val view: DashboardContract.View, private val dashboardModel: DashboardModel,
                          private val lifecycleScope: LifecycleCoroutineScope
) :
    DashboardContract.Presenter {
    override fun setProfileID() {
        val username = dashboardModel.getUser().username  // use email to query DB
        if (username.isEmpty()) return

        lifecycleScope.launch {
            val fullName = dashboardModel.getFullName(username)
            view.setProfileID(fullName)  // shows "Wild Adventurer X" instead of username
        }
    }
    override fun navToSoloRev(){
        view.navigateToSoloRev()
    }
    override fun navToGroupRev(){
        view.navigateToGroupRev()
    }
    override fun navToHome(){
        view.navigateToHome()
    }
    override fun navToLeaderboards(){
        view.navigateToLeaderboards()
    }
    override fun navToGroups(){
        view.navigateToGroups()
    }
    override fun navToProfile(){
        view.navigateToProfile()
    }
    override fun navToHistory(){
        view.navigateToHistory()
    }
    override fun navToSettings(){
        view.navigateToSettings()
    }
    override fun navToFAQ(){
        view.navigateToFAQ()
    }

    override fun logout() {
        view.showSuccessLogoutMsg()
        view.navigateToLogo()
    }
}