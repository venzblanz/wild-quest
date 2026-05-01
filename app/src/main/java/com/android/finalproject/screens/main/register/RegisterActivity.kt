package com.android.finalproject.screens.main.register

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.android.finalproject.R
import com.android.finalproject.screens.main.login.LoginActivity
import com.android.finalproject.utils.getButtonView
import com.android.finalproject.utils.getImgButtonView
import com.android.finalproject.utils.getTextInputEditText
import com.android.finalproject.utils.start
import com.android.finalproject.utils.toast

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        })

        presenter = RegisterPresenter(this, RegisterModel())

        getButtonView(R.id.reg_confirmbutton).setOnClickListener {
            val username =          getTextInputEditText(R.id.register_edittext_username)
            val password =          getTextInputEditText(R.id.register_edittext_password)
            val confirmpassword =   getTextInputEditText(R.id.register_edittext_confirmpassword)
            val email =             getTextInputEditText(R.id.register_edittext_email)

            presenter.register(username, password, confirmpassword, email)
        }

        getImgButtonView(R.id.reg_logobutton).setOnClickListener {
            presenter.navToLogin()
        }
    }

    override fun showSuccessMsg() {
        toast("Registered successfully!")
    }

    override fun showPasswordMismatch() {
        toast("Passwords doesn't match!")
    }

    override fun showInvalidCredentialsMsg() {
        toast("Username already exists!")
    }

    override fun showEmptyMsg() {
        toast("Please fill up all fields")
    }

    override fun navigateToLogin() {
        start(LoginActivity::class.java)
    }

    override fun genericError() {
        toast("Unexpected error happened")
    }
}