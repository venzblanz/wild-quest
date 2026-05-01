package com.android.finalproject.screens.main.soloreview.quiz

class SoloQuizPresenter (
    private val view: SoloQuizContract.View, private val soloQuizModel: SoloQuizModel
) : SoloQuizContract.Presenter{
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
        val correct = soloQuizModel.isCorrect(currIndex, answer)
        val question = soloQuizModel.getQuestion(currIndex)

        soloQuizModel.saveAnswer(currIndex, answer, correct)

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
        val question = soloQuizModel.getQuestion(currIndex)
        view.showAnswerSection(question.type)
        view.showQuestion(question, currIndex + 1, soloQuizModel.getTotalCount())

        // if already answered, just show the result — don't allow re-answering
        val answered = soloQuizModel.getAnsweredQuestion(currIndex)
        if (answered != null) {
            view.showPreviousAnswer(answered.selectedAnswer, question.correctAnswer)
        }
    }
    override fun nextQuestion(){
        currIndex++
        if(currIndex < soloQuizModel.getTotalCount()){
            showCurrentQuestion()
        }else{
            view.navigateToResults(score, soloQuizModel.getTotalCount())
        }
    }
    override fun cancelQuiz(){
        view.navigateToHome()
    }
}