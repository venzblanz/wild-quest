package com.android.finalproject.screens.main.groupreview.lobby.competitive.quiz

class GroupCompeQuizPresenter (
    private val view: GroupCompeQuizContract.View, private val groupCompeQuizModel: GroupCompeQuizModel
) : GroupCompeQuizContract.Presenter {
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
        val correct = groupCompeQuizModel.isCorrect(currIndex, answer)
        val question = groupCompeQuizModel.getQuestion(currIndex)

        groupCompeQuizModel.saveAnswer(currIndex, answer, correct)

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
        val question = groupCompeQuizModel.getQuestion(currIndex)
        view.showAnswerSection(question.type)
        view.showQuestion(question, currIndex + 1, groupCompeQuizModel.getTotalCount())

        // if already answered, just show the result — don't allow re-answering
        val answered = groupCompeQuizModel.getAnsweredQuestion(currIndex)
        if (answered != null) {
            view.showPreviousAnswer(answered.selectedAnswer, question.correctAnswer)
        }
    }
    override fun nextQuestion(){
        currIndex++
        if(currIndex < groupCompeQuizModel.getTotalCount()){
            showCurrentQuestion()
        }else{
            view.navigateToResults(score, groupCompeQuizModel.getTotalCount())
        }
    }
    override fun cancelQuiz(){
        view.navigateToHome()
    }
}