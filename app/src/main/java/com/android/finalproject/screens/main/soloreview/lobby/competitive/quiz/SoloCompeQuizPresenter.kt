package com.android.finalproject.screens.main.soloreview.lobby.competitive.quiz

import SoloCompeQuizContract
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.launch

class SoloCompeQuizPresenter(
    private val view: SoloCompeQuizContract.View,
    private val soloCompeQuizModel: SoloCompeQuizModel,
    private val lifecycleCoroutineScope: LifecycleCoroutineScope
) : SoloCompeQuizContract.Presenter {
    private var currIndex = 0
    private var score = 0

    fun getIndex(): Int {
        return currIndex
    }
    override fun startQuiz() {
        lifecycleCoroutineScope.launch {
            currIndex = 0
            score = 0

            soloCompeQuizModel.loadQuestions()

            if (soloCompeQuizModel.getTotalCount() > 0) {
                view.showScore(score)
                showCurrentQuestion()
            } else {
                // Optional: make a view function for this later
                view.navigateToHome()
            }
        }
    }

    override fun submitAnswer(answer: String) {
        val correct = soloCompeQuizModel.isCorrect(currIndex, answer)
        val question = soloCompeQuizModel.getQuestion(currIndex)

        soloCompeQuizModel.saveAnswer(currIndex, answer, correct)

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
        val question = soloCompeQuizModel.getQuestion(currIndex)

        view.showAnswerSection(question.type)
        view.showQuestion(question, currIndex + 1, soloCompeQuizModel.getTotalCount())

        val answered = soloCompeQuizModel.getAnsweredQuestion(currIndex)

        if (answered != null) {
            view.showPreviousAnswer(
                answered.selectedAnswer,
                question.questionAnswer
            )
        }
    }

    override fun nextQuestion() {
        currIndex++

        if (currIndex < soloCompeQuizModel.getTotalCount()) {
            showCurrentQuestion()
        } else {
            view.navigateToResults(score, soloCompeQuizModel.getTotalCount())
        }
    }

    override fun cancelQuiz() {
        view.navigateToHome()
    }
}