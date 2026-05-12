package com.android.finalproject.screens.main.soloreview.lobby.custom.prep

import com.android.finalproject.data.questions.QuizQuestion

interface SoloCustomPrepContract {

    interface View {
        fun showMultipleChoiceForm()
        fun showIdentificationForm()
        fun refreshQuestionList(questions: List<QuizQuestion>)
        fun showMessage(message: String)
        fun navigateToCustomQuiz(questions: ArrayList<QuizQuestion>)
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