package com.android.finalproject.screens.main.groupreview.lobby.custom.quiz

class GroupCustomQuizPresenter(
    private val view: GroupCustomQuizContract.View,
    private val groupCustomQuizModel: GroupCustomQuizModel
) : GroupCustomQuizContract.Presenter {

    private var currIndex = 0
    private var score = 0

    fun getIndex(): Int {
        return currIndex
    }

    override fun startQuiz() {
        currIndex = 0
        score = 0

        if (groupCustomQuizModel.getTotalCount() > 0) {
            view.showScore(score)
            showCurrentQuestion()
        } else {
            view.navigateToHome()
        }
    }

    override fun submitAnswer(answer: String) {
        val correct = groupCustomQuizModel.isCorrect(currIndex, answer)
        val question = groupCustomQuizModel.getQuestion(currIndex)

        groupCustomQuizModel.saveAnswer(currIndex, answer, correct)

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
        val question = groupCustomQuizModel.getQuestion(currIndex)

        view.showAnswerSection(question.type)
        view.showQuestion(question, currIndex + 1, groupCustomQuizModel.getTotalCount())

        val answered = groupCustomQuizModel.getAnsweredQuestion(currIndex)

        if (answered != null) {
            view.showPreviousAnswer(
                answered.selectedAnswer,
                question.questionAnswer
            )
        }
    }

    override fun nextQuestion() {
        currIndex++

        if (currIndex < groupCustomQuizModel.getTotalCount()) {
            showCurrentQuestion()
        } else {
            view.navigateToResults(score, groupCustomQuizModel.getTotalCount())
        }
    }

    override fun cancelQuiz() {
        view.navigateToHome()
    }
}