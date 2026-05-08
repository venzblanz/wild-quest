package com.android.finalproject.screens.main.groupreview.lobby.custom.room

interface GroupCustomRoomContract {
    interface View{
        fun navToGroupCustomQuiz()
        fun navToPrev()
    }
    interface Presenter{
        fun navigateToGroupCustomQuiz()

        fun navigateToPrevious()
    }
}