package com.android.finalproject.screens.main.soloreview.lobby.custom.quiz

class SoloCustomQuizPresenter (
    private val view: SoloCustomQuizContract.View, private val soloCustomQuizModel: SoloCustomQuizModel
) : SoloCustomQuizContract.Presenter {
    private var currIndex = 0
    private var score = 0

    // Helper
    fun getIndex(): Int{
        return currIndex
    }
    override fun startQuiz(){
        currIndex = 0
        score = 0
        showCurrentQuestion()
    }
    override fun submitAnswer(answer: String) {
        val correct = soloCustomQuizModel.isCorrect(currIndex, answer)
        val question = soloCustomQuizModel.getQuestion(currIndex)

        soloCustomQuizModel.saveAnswer(currIndex, answer, correct)

        if (correct) {
            score++
            view.showScore(score)
            view.highlightCorrect(question.correctAnswer)
        } else {
            view.highlightWrong(answer, question.correctAnswer)
        }
    }

    override fun previousQuestion() {
        if (currIndex > 0) {
            currIndex--
            showCurrentQuestion()
        }
    }

    private fun showCurrentQuestion() {
        val question = soloCustomQuizModel.getQuestion(currIndex)
        view.showAnswerSection(question.type)
        view.showQuestion(question, currIndex + 1, soloCustomQuizModel.getTotalCount())

        // if already answered, just show the result — don't allow re-answering
        val answered = soloCustomQuizModel.getAnsweredQuestion(currIndex)
        if (answered != null) {
            view.showPreviousAnswer(answered.selectedAnswer, question.correctAnswer)
        }
    }
    override fun nextQuestion(){
        currIndex++
        if(currIndex < soloCustomQuizModel.getTotalCount()){
            showCurrentQuestion()
        }else{
            view.navigateToResults(score, soloCustomQuizModel.getTotalCount())
        }
    }
    override fun cancelQuiz(){
        view.navigateToHome()
    }
}