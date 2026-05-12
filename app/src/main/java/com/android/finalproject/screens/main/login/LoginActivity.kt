package com.android.finalproject.screens.main.login

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.screens.main.dashboard.DashboardActivity
import com.android.finalproject.screens.main.register.RegisterActivity
import com.android.finalproject.utils.getButtonView
import com.android.finalproject.utils.getTextInputEditText
import com.android.finalproject.utils.loadScreen
import com.android.finalproject.utils.start
import com.android.finalproject.utils.toast

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        })

        presenter = LoginPresenter(this, LoginModel(this), lifecycleScope)

        getButtonView(R.id.login_loginbutton).setOnClickListener {
            val username = getTextInputEditText(R.id.login_edittext_username)
            val password = getTextInputEditText(R.id.login_edittext_password)
            presenter.login(username, password)
        }

        getButtonView(R.id.login_registerbutton).setOnClickListener {
            presenter.navToReg()
        }
    }

    override fun navigateToRegister() {
        start(RegisterActivity::class.java)
    }


    override fun showSuccessMsg() {
        toast("Login successful!")
    }

    override fun showInvalidCredentialsMsg() {
        toast("Invalid credentials!")
    }

    override fun showEmptyMsg() {
        toast("Please fill up all fields")
    }

    override fun navigateToDashboard() {
        loadScreen(this,DashboardActivity::class.java)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    override fun genericError() {
        toast("Unexpected error happened")
    }
}