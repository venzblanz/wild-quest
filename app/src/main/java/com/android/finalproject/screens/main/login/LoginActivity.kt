package com.android.finalproject.screens.main.login

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.android.finalproject.R
import com.android.finalproject.screens.main.dashboard.DashboardActivity
import com.android.finalproject.screens.sub.logopage.LogoPageActivity
import com.android.finalproject.screens.main.register.RegisterActivity
import com.android.finalproject.utils.getButtonView
import com.android.finalproject.utils.getImgButtonView
import com.android.finalproject.utils.getTextInputEditText
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

        presenter = LoginPresenter(this, LoginModel())

        getButtonView(R.id.login_loginbutton).setOnClickListener {
            val username = getTextInputEditText(R.id.login_edittext_username)
            val password = getTextInputEditText(R.id.login_edittext_password)
            presenter.login(username, password)
        }

        getButtonView(R.id.login_registerbutton).setOnClickListener {
            presenter.navToReg()
        }

        getImgButtonView(R.id.login_logobutton).setOnClickListener {
            presenter.navToLogo()
        }
    }

    override fun navigateToRegister() {
        start(RegisterActivity::class.java)
    }

    override fun navigateToLogo() {
        start(LogoPageActivity::class.java)
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
        start(DashboardActivity::class.java)
        finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun genericError() {
        toast("Unexpected error happened")
    }
}