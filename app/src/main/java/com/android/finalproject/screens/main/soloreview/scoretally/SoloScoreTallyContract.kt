package com.android.finalproject.screens.main.soloreview.scoretally

interface SoloScoreTallyContract {
    interface View{
        // score display
        fun showScore(score: Int, total: Int)


        // navigation
        fun navigateToHome()


        // highlighting
        fun highlightPass()
        fun highlightFail()
        fun highlightGood()
    }
    interface Presenter{
        fun displayScore(score: Int, total: Int)
        fun navToHome()
    }
}