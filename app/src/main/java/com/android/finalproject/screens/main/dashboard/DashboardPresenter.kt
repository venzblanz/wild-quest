package com.android.finalproject.screens.main.dashboard

class DashboardPresenter (private val view: DashboardContract.View, private val dashboardModel: DashboardModel) :
    DashboardContract.Presenter {
    override fun setProfileID() {
        if(!dashboardModel.getUser().username.isNullOrEmpty()){
            view.setProfileID("${dashboardModel.getUser().username}")
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