package com.android.finalproject.screens.main.soloreview.lobby.custom.scoretally

class SoloCustomScoreTallyPresenter (
    private val view: SoloCustomScoreTallyContract.View
): SoloCustomScoreTallyContract.Presenter {

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