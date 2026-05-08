package com.android.finalproject.screens.main.soloreview.lobby.competitive.quiz

class SoloCompeQuizPresenter (
    private val view: SoloCompeQuizContract.View, private val soloCompeQuizModel: SoloCompeQuizModel
) : SoloCompeQuizContract.Presenter {
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
        val correct = soloCompeQuizModel.isCorrect(currIndex, answer)
        val question = soloCompeQuizModel.getQuestion(currIndex)

        soloCompeQuizModel.saveAnswer(currIndex, answer, correct)

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
        val question = soloCompeQuizModel.getQuestion(currIndex)
        view.showAnswerSection(question.type)
        view.showQuestion(question, currIndex + 1, soloCompeQuizModel.getTotalCount())

        // if already answered, just show the result — don't allow re-answering
        val answered = soloCompeQuizModel.getAnsweredQuestion(currIndex)
        if (answered != null) {
            view.showPreviousAnswer(answered.selectedAnswer, question.correctAnswer)
        }
    }
    override fun nextQuestion(){
        currIndex++
        if(currIndex < soloCompeQuizModel.getTotalCount()){
            showCurrentQuestion()
        }else{
            view.navigateToResults(score, soloCompeQuizModel.getTotalCount())
        }
    }
    override fun cancelQuiz(){
        view.navigateToHome()
    }
}