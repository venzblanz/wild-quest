package com.android.finalproject.screens.main.groupreview.lobby.competitive.room

class GroupCompeRoomPresenter (private val view: GroupCompeRoomContract.View) : GroupCompeRoomContract.Presenter{
    override fun navigateToGroupCompeQuiz(){
        view.navToGroupCompeQuiz()
    }
    override fun navigateToPrevious(){
        view.navToPrev()
    }
}