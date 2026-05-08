package com.android.finalproject.screens.main.groupreview.lobby.competitive.room

interface GroupCompeRoomContract {
    interface View{
        fun navToGroupCompeQuiz()
        fun navToPrev()
    }
    interface Presenter{
        fun navigateToGroupCompeQuiz()
        fun navigateToPrevious()
    }
}