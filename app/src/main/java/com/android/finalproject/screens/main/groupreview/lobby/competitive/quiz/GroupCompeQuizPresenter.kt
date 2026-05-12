package com.android.finalproject.screens.main.groupreview.lobby.competitive.quiz

import SoloCompeQuizContract
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.launch

class GroupCompeQuizPresenter (
    private val view: GroupCompeQuizContract.View,
    private val groupCompeQuizModel: GroupCompeQuizModel,
    private val lifecycleCoroutineScope: LifecycleCoroutineScope
) : GroupCompeQuizContract.Presenter {
    private var currIndex = 0
    private var score = 0

    // Helper
    fun getIndex(): Int{
        return currIndex
    }
    override fun startQuiz(){
        lifecycleCoroutineScope.launch {
            currIndex = 0
            score = 0

            groupCompeQuizModel.loadQuestions()

            if (groupCompeQuizModel.getTotalCount() > 0) {
                view.showScore(score)
                showCurrentQuestion()
            } else {
                // Optional: make a view function for this later
                view.navigateToHome()
            }
        }
    }
    override fun submitAnswer(answer: String) {
        val correct = groupCompeQuizModel.isCorrect(currIndex, answer)
        val question = groupCompeQuizModel.getQuestion(currIndex)

        groupCompeQuizModel.saveAnswer(currIndex, answer, correct)

        if (correct) {
            score++
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
        val question = groupCompeQuizModel.getQuestion(currIndex)

        view.showAnswerSection(question.type)
        view.showQuestion(question, currIndex + 1, groupCompeQuizModel.getTotalCount())

        val answered = groupCompeQuizModel.getAnsweredQuestion(currIndex)

        if (answered != null) {
            view.showPreviousAnswer(
                answered.selectedAnswer,
                question.questionAnswer
            )
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