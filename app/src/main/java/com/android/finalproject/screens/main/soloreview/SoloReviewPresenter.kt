package com.android.finalproject.screens.main.soloreview

class SoloReviewPresenter (private val view: SoloReviewContract.View,
                           private val soloReviewModel: SoloReviewModel
                            ) : SoloReviewContract.Presenter {
    override fun setProfileID() {
        if(!soloReviewModel.getUser().username.isNullOrEmpty()){
            view.setProfileID("${soloReviewModel.getUser().username}")
        }
    }
    override fun navToCompeRoom(){
        view.navigateToCompetitiveRoom()
    }
    override fun navToCusPrep(){
        view.navigateToCustomPrep()
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