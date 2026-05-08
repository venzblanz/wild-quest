package com.android.finalproject.screens.main.soloreview.lobby.custom.prep

import com.android.finalproject.data.questions.Questions

class SoloCustomPrepPresenter(
    private val view: SoloCustomPrepContract.View,
    private val model: SoloCustomPrepModel
) : SoloCustomPrepContract.Presenter {

    override fun onTypeSelected(type: String) {
        if (type == "MC") {
            view.showMultipleChoiceForm()
        } else {
            view.showIdentificationForm()
        }
    }

    override fun addMultipleChoiceQuestion(
        question: String,
        optionA: String,
        optionB: String,
        optionC: String,
        optionD: String,
        answer: String
    ) {
        if (
            question.isBlank() ||
            optionA.isBlank() ||
            optionB.isBlank() ||
            optionC.isBlank() ||
            optionD.isBlank() ||
            answer.isBlank()
        ) {
            view.showMessage("Please complete all fields")
            return
        }

        val customQuestion = Questions(
            question = question,
            choices = listOf(optionA, optionB, optionC, optionD),
            correctAnswer = answer,
            type = "mulitple_choice"
        )

        model.addQuestion(customQuestion)
        view.refreshQuestionList(model.getQuestions())
        view.showMessage("Question added")
    }

    override fun addIdentificationQuestion(question: String, answer: String) {
        if (question.isBlank() || answer.isBlank()) {
            view.showMessage("Please complete all fields")
            return
        }

        val customQuestion = Questions(
            question = question,
            choices = emptyList(),
            correctAnswer = answer,
            type = "identification"
        )

        model.addQuestion(customQuestion)
        view.refreshQuestionList(model.getQuestions())
        view.showMessage("Question added")
    }

    override fun deleteQuestion(position: Int) {
        model.deleteQuestion(position)
        view.refreshQuestionList(model.getQuestions())
    }

    override fun startReview() {
        if (!model.hasQuestions()) {
            view.showMessage("Add at least one question")
            return
        }

        view.navigateToCustomQuiz(model.getQuestions())
    }
}