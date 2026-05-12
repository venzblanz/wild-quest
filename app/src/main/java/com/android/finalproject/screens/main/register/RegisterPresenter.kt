package com.android.finalproject.screens.main.register

import android.app.Activity
import androidx.lifecycle.LifecycleCoroutineScope
import com.android.finalproject.data.users.Users
import com.android.finalproject.utils.app
import kotlinx.coroutines.launch

class RegisterPresenter(
    private val view: RegisterContract.View,
    private val registerModel: RegisterModel,
    private val lifecycleScope: LifecycleCoroutineScope
) : RegisterContract.Presenter {

    private val app = (view as Activity).app()

    override fun register(username: String, password: String, confirmpassword: String, email: String) {
        if (username.isEmpty() && password.isEmpty() && confirmpassword.isEmpty() && email.isEmpty()) {
            view.showEmptyMsg()
        }
        if (password != confirmpassword) {
            view.showPasswordMismatch()
        }
        lifecycleScope.launch {
            val success = registerModel.register(username, password, email)
            if (success) {
                app.setUser(Users(username, password, email))
                view.showSuccessMsg()
                view.navigateToLogin()
            } else {
                view.showInvalidCredentialsMsg()
            }
        }
    }

    override fun navToLogin() {
        view.navigateToLogin()
    }
}