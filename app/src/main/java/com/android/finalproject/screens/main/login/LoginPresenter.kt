package com.android.finalproject.screens.main.login

import android.app.Activity
import androidx.lifecycle.LifecycleCoroutineScope
import com.android.finalproject.data.users.Users
import com.android.finalproject.utils.app
import kotlinx.coroutines.launch

class LoginPresenter(
    private val view: LoginContract.View,
    private val loginModel: LoginModel,
    private val lifecycleScope: LifecycleCoroutineScope
) : LoginContract.Presenter {

    private val app = (view as Activity).app()

    override fun login(username: String, password: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            lifecycleScope.launch {
                val userEntity = loginModel.login(username, password)

                if (userEntity != null) {
                    app.setUser(
                        Users(
                            username = userEntity.username,
                            password = userEntity.password,
                            email    = userEntity.email
                        )
                    )
                    view.showSuccessMsg()
                    view.navigateToDashboard()
                } else {
                    view.showInvalidCredentialsMsg()
                }
            }
        } else {
            view.showEmptyMsg()
        }
    }

    override fun navToReg() {
        view.navigateToRegister()
    }
}