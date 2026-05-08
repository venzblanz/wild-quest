package com.android.finalproject.screens.main.soloreview.lobby.competitive.loading

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.screens.main.soloreview.SoloReviewActivity
import com.android.finalproject.screens.main.soloreview.lobby.competitive.quiz.SoloCompeQuizActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class SoloCompeLoadingActivity : AppCompatActivity() {

    private lateinit var loadingText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_solo_review_loading)

        loadingText = findViewById(R.id.loadingText)

        startPulseAnimation()

        lifecycleScope.launch {
            findViewById<LinearLayout>(R.id.cancelBtn).setOnClickListener{
                startActivity(Intent(this@SoloCompeLoadingActivity, SoloReviewActivity::class.java))
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                finish()
            }
            val delayTime = Random.nextLong(1000, 5000)
            delay(delayTime)

            loadingText.text = "Rivals found! Starting in a moment"
            findViewById<LinearLayout>(R.id.cancelBtn).isEnabled = false
            findViewById<LinearLayout>(R.id.cancelBtn).isVisible = false
            onBackPressedDispatcher.addCallback(this@SoloCompeLoadingActivity, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // do nothing to disable back button
                }
            })

            val delayTime2 = Random.nextLong(1000, 5000)
            delay(delayTime2)

            startActivity(Intent(this@SoloCompeLoadingActivity, SoloCompeQuizActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }

    private fun startPulseAnimation() {
        loadingText.animate()
            .alpha(0.3f)
            .setDuration(500)
            .withEndAction {
                loadingText.animate()
                    .alpha(1f)
                    .setDuration(500)
                    .withEndAction {
                        startPulseAnimation()
                    }
            }
    }
}