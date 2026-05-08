package com.android.finalproject.screens.main.groupreview.lobby.custom.prep

import com.android.finalproject.data.questions.Questions

class GroupCustomPrepModel {
    private val questions = ArrayList<Questions>()

    fun addQuestion(question: Questions) {
        questions.add(question)
    }

    fun deleteQuestion(position: Int) {
        if (position in questions.indices) {
            questions.removeAt(position)
        }
    }

    fun getQuestions(): ArrayList<Questions> {
        return ArrayList(questions)
    }

    fun hasQuestions(): Boolean {
        return questions.isNotEmpty()
    }
}