package com.android.finalproject.data.questions

import com.android.finalproject.data.tables.QuestionEntity

fun QuestionEntity.toQuizQuestion(): QuizQuestion {
    return QuizQuestion(
        id = this.questionId,
        questionText = this.questionText,
        questionAnswer = this.questionAnswer,
        points = this.points,
        category = this.category,
        type = this.type,
        optionA = this.optionA,
        optionB = this.optionB,
        optionC = this.optionC,
        optionD = this.optionD
    )
}