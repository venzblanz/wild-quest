package com.android.finalproject.screens.main.register

interface RegisterContract {
    interface View {
        fun showSuccessMsg()
        fun showInvalidCredentialsMsg()
        fun showPasswordMismatch()
        fun showEmptyMsg()
        fun navigateToLogin()
        fun genericError()
    }
    interface Presenter {
        fun register(username: String, password: String, confirmpassword: String, email:String)
        fun navToLogin()
    }
}