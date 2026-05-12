package com.android.finalproject.screens.sub.profile

interface ProfileContract {
    interface View {
        fun navigateToHome()
        fun setEmail(email: String)
        fun setProfileID(id: String)

        // ── Stats display — called by Presenter after loading from Room ──
        fun setQuizCount(count: String)
        fun setTotalScore(score: String)
        fun setBestScore(score: String)
        fun setAccuracy(accuracy: String)
    }

    interface Presenter {
        fun navToHome()
        fun setProfileID()
        fun setEmail()

        // ── New: load stats from the Room database ──
        fun loadStats()
    }
}