package com.android.finalproject.screens.main.login

import android.app.Activity
import com.android.finalproject.data.users.UserStore
import com.android.finalproject.utils.app

class LoginPresenter(
    private val view: LoginContract.View,
    private val loginModel: LoginModel
) : LoginContract.Presenter {

    private val app = (view as Activity).app()

    override fun login(username: String, password: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            if (loginModel.login(username, password)) {
                val user = UserStore.getUser(username)
                if(user != null){
                    app.setUser(user)
                }
                view.showSuccessMsg()
                view.navigateToDashboard()
            } else {
                view.showInvalidCredentialsMsg()
            }
        } else {
            view.showEmptyMsg()
        }
    }

    override fun navToLogo() {
        view.navigateToLogo()
    }

    override fun navToReg() {
        view.navigateToRegister()
    }
}