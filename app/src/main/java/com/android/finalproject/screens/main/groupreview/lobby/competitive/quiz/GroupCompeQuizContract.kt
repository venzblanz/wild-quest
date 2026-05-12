package com.android.finalproject.screens.main.groupreview.lobby.competitive.quiz

import com.android.finalproject.data.questions.QuizQuestion

interface GroupCompeQuizContract {
    interface View {
        fun showAnswerSection(type: String)
        fun showQuestion(question: QuizQuestion, questionNumber: Int, total: Int)
        fun showPreviousAnswer(selectedAnswer: String, correctAnswer: String)
        fun showScore(score: Int)
        fun highlightCorrect(correctAnswer: String)
        fun highlightWrong(selectedAnswer: String, correctAnswer: String)
        fun navigateToResults(score: Int, total: Int)
        fun navigateToHome()
    }

    interface Presenter {
        fun startQuiz()
        fun submitAnswer(answer: String)
        fun previousQuestion()
        fun nextQuestion()
        fun cancelQuiz()
    }
}