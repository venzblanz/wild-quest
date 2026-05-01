package com.android.finalproject.screens.main.soloreview.quiz

import com.android.finalproject.data.questions.Questions

interface SoloQuizContract {
    interface View {
        // question display
        fun showQuestion(question: Questions, questionNumber: Int, total: Int)
        fun showAnswerSection(type: String)
        fun showScore(score: Int)

        // answer feedback
        fun highlightCorrect(correctAnswer: String)
        fun highlightWrong(selectedAnswer: String, correctAnswer: String)

        // navigation
        fun showPreviousAnswer(selectedAnswer: String, correctAnswer: String)
        fun navigateToResults(score: Int, total: Int)
        fun navigateToHome()
    }
    interface Presenter {
        fun startQuiz()
        fun submitAnswer(answer: String)
        fun previousQuestion();
        fun nextQuestion()
        fun cancelQuiz()
    }
}