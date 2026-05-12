package com.android.finalproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.finalproject.data.tables.CustomQuestionEntity
import com.android.finalproject.data.tables.QuestionEntity
import com.android.finalproject.data.tables.QuizAttemptEntity
import com.android.finalproject.data.tables.UserEntity

@Database(
    entities = [
        UserEntity::class,
        QuestionEntity::class,
        CustomQuestionEntity::class,
        QuizAttemptEntity::class
    ],
    version = 9,
    exportSchema = false
)
abstract class WildQuestDatabase : RoomDatabase(){
    abstract fun userDao(): UserDAO
    abstract fun questionDao(): QuestionDAO
    abstract fun customQuestionDao(): CustomQuestionDAO
    abstract fun quizAttemptDao(): QuizAttemptDAO

    companion object {
        @Volatile
        private var INSTANCE: WildQuestDatabase? = null

        fun getDatabase(context: Context): WildQuestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WildQuestDatabase::class.java,
                    "wildquest_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}