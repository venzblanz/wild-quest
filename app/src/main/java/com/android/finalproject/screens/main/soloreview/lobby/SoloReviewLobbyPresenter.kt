package com.android.finalproject.screens.main.soloreview.lobby

class SoloReviewLobbyPresenter (private val view: SoloReviewLobbyContract.View,
                                private val soloReviewLobbyModel: SoloReviewLobbyModel
                            ) : SoloReviewLobbyContract.Presenter {
    override fun setProfileID() {
        if(!soloReviewLobbyModel.getUser().username.isNullOrEmpty()){
            view.setProfileID("${soloReviewLobbyModel.getUser().username}")
        }
    }
    override fun navToCompeRoom(){
        view.navigateToCompetitiveRoom()
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