package com.android.finalproject.screens.main.groupreview

class GroupReviewPresenter (private val view: GroupReviewContract.View,
                            private val groupReviewModel: GroupReviewModel
                            ) : GroupReviewContract.Presenter {
    override fun setProfileID() {
        if(!groupReviewModel.getUser().username.isNullOrEmpty()){
            view.setProfileID("${groupReviewModel.getUser().username}")
        }
    }
    override fun navToCompeRoom(){
        view.navigateToCompetitiveRoom()
    }
    override fun navToCustRoom(){
        view.navigateToCustomRoom()
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