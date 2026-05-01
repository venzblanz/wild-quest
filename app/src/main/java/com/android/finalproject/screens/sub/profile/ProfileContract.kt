package com.android.finalproject.screens.sub.profile

interface ProfileContract {
    interface View {
        fun navigateToHome()
        fun setEmail(email: String)
        fun setProfileID(id: String)

    }
    interface Presenter {
        fun navToHome()
        fun setProfileID()
        fun setEmail()
    }
}