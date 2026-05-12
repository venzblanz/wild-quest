package com.android.finalproject.screens.main.groupreview.lobby.custom.prep

import com.android.finalproject.data.questions.QuizQuestion

class GroupCustomPrepPresenter(
    private val view: GroupCustomPrepContract.View,
    private val model: GroupCustomPrepModel
) : GroupCustomPrepContract.Presenter {

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

        val customQuestion = QuizQuestion(
            questionText = question,
            questionAnswer = answer,
            points = 1,
            category = "Custom",
            type = "Multiple Choice",
            optionA = optionA,
            optionB = optionB,
            optionC = optionC,
            optionD = optionD
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

        val customQuestion = QuizQuestion(
            questionText = question,
            questionAnswer = answer,
            points = 1,
            category = "Custom",
            type = "Identification",
            optionA = "",
            optionB = "",
            optionC = "",
            optionD = ""
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