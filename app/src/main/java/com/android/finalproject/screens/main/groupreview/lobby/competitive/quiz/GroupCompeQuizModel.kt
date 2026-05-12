package com.android.finalproject.screens.main.groupreview.lobby.competitive.quiz

import android.content.Context
import com.android.finalproject.data.database.WildQuestDatabase
import com.android.finalproject.data.questions.AnsweredQuestion
import com.android.finalproject.data.questions.QuizQuestion
import com.android.finalproject.data.questions.toQuizQuestion

class GroupCompeQuizModel(private val context: Context) {
    private val db = WildQuestDatabase.getDatabase(context)
    private var questions: List<QuizQuestion> = emptyList()
    private val answeredQuestions: MutableMap<Int, AnsweredQuestion> = mutableMapOf()

    suspend fun loadQuestions() {
        questions = db.questionDao()
            .getRandomQuestions(20)
            .map { it.toQuizQuestion() }
    }
    fun getQuestions(): List<QuizQuestion> {
        return questions
    }
    fun getTotalCount(): Int {
        return questions.size
    }
    fun getQuestion(index: Int): QuizQuestion {
        return questions[index]
    }
    fun isCorrect(index: Int, answer: String): Boolean {
        return questions[index].questionAnswer.trim()
            .equals(answer.trim(), ignoreCase = true)
    }
    fun saveAnswer(index: Int, selected: String, correct: Boolean) {
        answeredQuestions[index] = AnsweredQuestion(
            question = questions[index],
            selectedAnswer = selected,
            isCorrect = correct
        )
    }
    fun getAnsweredQuestion(index: Int): AnsweredQuestion? {
        return answeredQuestions[index]
    }
    fun isAnswered(index: Int): Boolean {
        return answeredQuestions.containsKey(index)
    }
}