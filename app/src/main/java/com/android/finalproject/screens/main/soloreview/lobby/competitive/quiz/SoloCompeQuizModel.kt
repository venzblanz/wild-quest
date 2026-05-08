package com.android.finalproject.screens.main.soloreview.lobby.competitive.quiz

import com.android.finalproject.data.questions.AnsweredQuestion
import com.android.finalproject.data.questions.QuestionStore
import com.android.finalproject.data.questions.Questions

class SoloCompeQuizModel {
    private val questions: List<Questions> = QuestionStore.questions.shuffled()
    private val answeredQuestions: MutableMap<Int, AnsweredQuestion> = mutableMapOf()
    fun getQuestions(): List<Questions> = questions
    fun getTotalCount(): Int = questions.size
    fun getQuestion(index: Int): Questions = questions[index]
    fun isCorrect(index: Int, answer: String): Boolean{
        return questions[index].correctAnswer.trim().equals(answer.trim(),ignoreCase = true)
    }
    fun saveAnswer(index: Int, selected: String, correct: Boolean) {
        answeredQuestions[index] = AnsweredQuestion(questions[index], selected, correct)
    }
    fun getAnsweredQuestion(index: Int): AnsweredQuestion? = answeredQuestions[index]

    fun isAnswered(index: Int): Boolean = answeredQuestions.containsKey(index)
}