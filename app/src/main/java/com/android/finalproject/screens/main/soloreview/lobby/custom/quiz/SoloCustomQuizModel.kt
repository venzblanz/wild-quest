package com.android.finalproject.screens.main.soloreview.lobby.custom.quiz

import com.android.finalproject.data.questions.AnsweredQuestion
import com.android.finalproject.data.questions.QuizQuestion

class SoloCustomQuizModel(private val activity: SoloCustomQuizActivity) {
    private val questions: List<QuizQuestion> = activity.getQuestion()!!.shuffled()
    private val answeredQuestions: MutableMap<Int, AnsweredQuestion> = mutableMapOf()
    fun getQuestions(): List<QuizQuestion> = questions
    fun getTotalCount(): Int = questions.size
    fun getQuestion(index: Int): QuizQuestion = questions[index]
    fun isCorrect(index: Int, answer: String): Boolean{
        return questions[index].questionAnswer.trim().equals(answer.trim(),ignoreCase = true)
    }
    fun saveAnswer(index: Int, selected: String, correct: Boolean) {
        answeredQuestions[index] = AnsweredQuestion(questions[index], selected, correct)
    }
    fun getAnsweredQuestion(index: Int): AnsweredQuestion? = answeredQuestions[index]

    fun isAnswered(index: Int): Boolean = answeredQuestions.containsKey(index)
}