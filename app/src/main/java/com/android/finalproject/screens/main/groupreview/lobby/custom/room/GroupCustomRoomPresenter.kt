package com.android.finalproject.screens.main.groupreview.lobby.custom.room

class GroupCustomRoomPresenter (private val view: GroupCustomRoomContract.View) : GroupCustomRoomContract.Presenter{
    override fun navigateToGroupCustomQuiz(){
        view.navToGroupCustomQuiz()
    }
    override fun navigateToPrevious(){
        view.navToPrev()
    }
}