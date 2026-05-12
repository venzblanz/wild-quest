package com.android.finalproject.screens.main.groupreview.lobby.custom.quiz

import com.android.finalproject.data.questions.QuizQuestion

interface GroupCustomQuizContract {
    interface View {
        fun showQuestion(question: QuizQuestion, questionNumber: Int, total: Int)
        fun showAnswerSection(type: String)
        fun showScore(score: Int)

        fun highlightCorrect(correctAnswer: String)
        fun highlightWrong(selectedAnswer: String, correctAnswer: String)

        fun showPreviousAnswer(selectedAnswer: String, correctAnswer: String)
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