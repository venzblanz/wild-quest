package com.android.finalproject.screens.sub.profile

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.screens.main.dashboard.DashboardActivity
import com.android.finalproject.utils.getButtonView
import com.android.finalproject.utils.setTextViewText
import com.android.finalproject.utils.start

class ProfileActivity : AppCompatActivity(), ProfileContract.View {

    private lateinit var presenter: ProfilePresenter

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

        presenter = ProfilePresenter(
            view           = this,
            profileModel   = ProfileModel(application as WildQuestApp),
            lifecycleScope = lifecycleScope   // ← the key addition
        )

        presenter.setProfileID()
        presenter.setEmail()
        presenter.loadStats()

        getButtonView(R.id.profile_backtohomebtn).setOnClickListener {
            presenter.navToHome()
        }
    }

    // ── ProfileContract.View implementations ────────────────────

    override fun navigateToHome() {
        start(DashboardActivity::class.java)
    }

    override fun setProfileID(id: String) {
        setTextViewText(R.id.profile_profiled_id, id)
    }

    override fun setEmail(email: String) {
        setTextViewText(R.id.profile_name, email)
    }

    // ── New stat views — IDs must exist in activity_profile.xml ──

    override fun setQuizCount(count: String) {
        setTextViewText(R.id.profile_quiz_count, count)
    }

    override fun setTotalScore(score: String) {
        setTextViewText(R.id.profile_total_score, score)
    }

    override fun setBestScore(score: String) {
        setTextViewText(R.id.profile_best_score, score)
    }

    override fun setAccuracy(accuracy: String) {
        setTextViewText(R.id.profile_accuracy, accuracy)
    }
}