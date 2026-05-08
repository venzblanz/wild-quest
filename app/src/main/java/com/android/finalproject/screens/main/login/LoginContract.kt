package com.android.finalproject.screens.main.login

interface LoginContract {
    interface View{
        fun showSuccessMsg()
        fun showInvalidCredentialsMsg()
        fun showEmptyMsg()
        fun navigateToDashboard()
        fun genericError()
        fun navigateToRegister()
    }
    interface Presenter {
        fun login(username: String, password: String)
        fun navToReg()
    }
}