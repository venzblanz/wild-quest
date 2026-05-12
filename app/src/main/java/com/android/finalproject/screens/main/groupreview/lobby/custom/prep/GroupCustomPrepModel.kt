package com.android.finalproject.screens.main.groupreview.lobby.custom.prep

import com.android.finalproject.data.questions.QuizQuestion

class GroupCustomPrepModel {
    private val questions = ArrayList<QuizQuestion>()

    fun addQuestion(question: QuizQuestion) {
        questions.add(question)
    }

    fun deleteQuestion(position: Int) {
        if (position in questions.indices) {
            questions.removeAt(position)
        }
    }

    fun getQuestions(): ArrayList<QuizQuestion> {
        return ArrayList(questions)
    }

    fun hasQuestions(): Boolean {
        return questions.isNotEmpty()
    }
}