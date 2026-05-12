package com.android.finalproject.screens.main.soloreview.lobby.custom.quiz

class SoloCustomQuizPresenter(
    private val view: SoloCustomQuizContract.View,
    private val soloCustomQuizModel: SoloCustomQuizModel
) : SoloCustomQuizContract.Presenter {

    private var currIndex = 0
    private var score = 0

    fun getIndex(): Int {
        return currIndex
    }

    override fun startQuiz() {
        currIndex = 0
        score = 0

        if (soloCustomQuizModel.getTotalCount() > 0) {
            view.showScore(score)
            showCurrentQuestion()
        } else {
            view.navigateToHome()
        }
    }

    override fun submitAnswer(answer: String) {
        val correct = soloCustomQuizModel.isCorrect(currIndex, answer)
        val question = soloCustomQuizModel.getQuestion(currIndex)

        soloCustomQuizModel.saveAnswer(currIndex, answer, correct)

        if (correct) {
            score += question.points
            view.showScore(score)
            view.highlightCorrect(question.questionAnswer)
        } else {
            view.highlightWrong(answer, question.questionAnswer)
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

        val answered = soloCustomQuizModel.getAnsweredQuestion(currIndex)

        if (answered != null) {
            view.showPreviousAnswer(
                answered.selectedAnswer,
                question.questionAnswer
            )
        }
    }

    override fun nextQuestion() {
        currIndex++

        if (currIndex < soloCustomQuizModel.getTotalCount()) {
            showCurrentQuestion()
        } else {
            view.navigateToResults(score, soloCustomQuizModel.getTotalCount())
        }
    }

    override fun cancelQuiz() {
        view.navigateToHome()
    }
}