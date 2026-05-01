package com.android.finalproject.screens.main.register

import android.app.Activity
import com.android.finalproject.data.users.Users
import com.android.finalproject.utils.app

class RegisterPresenter(
    private val view: RegisterContract.View,
    private val registerModel: RegisterModel
) : RegisterContract.Presenter {

    private val app = (view as Activity).app()

    override fun register(username: String, password: String, confirmpassword: String, email: String) {
        if (username.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty() && email.isNotEmpty()) {
            if (password.equals(confirmpassword)) {
                if(registerModel.register(username, password, email)){
                    app.setUser(Users(username, password, email))
                    view.showSuccessMsg()
                    view.navigateToLogin()
                }else{
                    view.showInvalidCredentialsMsg()
                }
            } else {
                view.showPasswordMismatch()
            }
        } else {
            view.showEmptyMsg()
        }
    }

    override fun navToLogin() {
        view.navigateToLogin()
    }
}