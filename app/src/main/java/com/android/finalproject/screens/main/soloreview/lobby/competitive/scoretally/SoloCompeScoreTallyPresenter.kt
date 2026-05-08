package com.android.finalproject.screens.main.soloreview.lobby.competitive.scoretally

class SoloCompeScoreTallyPresenter (
        private val view: SoloCompeScoreTallyContract.View
    ): SoloCompeScoreTallyContract.Presenter {

    override fun displayScore(score: Int, total: Int){
        view.showScore(score,total)
        val percent = score.toFloat() / total

        when {
            percent >= 0.9 -> view.highlightGood()
            percent >= 0.75 -> view.highlightPass()
            else -> view.highlightFail()
        }
    }
    override fun navToHome(){
        view.navigateToHome()
    }
}