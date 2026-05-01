package com.android.finalproject.screens.sub.logopage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.android.finalproject.R
import com.android.finalproject.screens.main.login.LoginActivity
import com.android.finalproject.utils.getImgButtonView
import com.android.finalproject.utils.start

class LogoPageActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_page)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        })

        getImgButtonView(R.id.logoButton).setOnClickListener{
            start(LoginActivity::class.java)
        }
    }
}