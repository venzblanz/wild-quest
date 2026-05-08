package com.android.finalproject.screens.main.soloreview.lobby.custom.prep

import com.android.finalproject.data.questions.Questions

interface SoloCustomPrepContract {

    interface View {
        fun showMultipleChoiceForm()
        fun showIdentificationForm()
        fun refreshQuestionList(questions: List<Questions>)
        fun showMessage(message: String)
        fun navigateToCustomQuiz(questions: ArrayList<Questions>)
    }

    interface Presenter {
        fun onTypeSelected(type: String)
        fun addMultipleChoiceQuestion(
            question: String,
            optionA: String,
            optionB: String,
            optionC: String,
            optionD: String,
            answer: String
        )
        fun addIdentificationQuestion(
            question: String,
            answer: String
        )
        fun deleteQuestion(position: Int)
        fun startReview()
    }
}