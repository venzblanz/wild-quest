package com.android.finalproject.data.seeders

import android.content.Context
import com.android.finalproject.data.database.QuestionDAO
import com.android.finalproject.data.tables.QuestionEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuestionSeeder(
    private val context: Context,
    private val questionDAO: QuestionDAO
) {
    suspend fun seedQuestionsIfEmpty() {
        val count = questionDAO.getQuestionCount()

        if (count == 0) {
            val questions = loadQuestionsFromJson()
            questionDAO.insertQuestions(questions)
        }
    }

    private fun loadQuestionsFromJson(): List<QuestionEntity> {
        val json = context.assets.open("questions.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<QuestionEntity>>() {}.type

        return Gson().fromJson(json, type)
    }
}