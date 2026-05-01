package com.android.finalproject.screens.sub.profile

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.android.finalproject.R
import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.screens.main.dashboard.DashboardActivity
import com.android.finalproject.utils.getButtonView
import com.android.finalproject.utils.setTextViewText
import com.android.finalproject.utils.start

class ProfileActivity : AppCompatActivity(), ProfileContract.View {
    private lateinit var presenter : ProfilePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        })
        presenter = ProfilePresenter(this, ProfileModel(application as WildQuestApp))
        presenter.setProfileID()
        presenter.setEmail()
        getButtonView(R.id.profile_backtohomebtn).setOnClickListener {
            presenter.navToHome()
        }
    }

    override fun navigateToHome() {
        start(DashboardActivity::class.java)
    }

    override fun setProfileID(id: String) {
        setTextViewText(R.id.profile_profiled_id, id)
    }

    override fun setEmail(email: String) {
        setTextViewText(R.id.profile_name,email)
    }
}