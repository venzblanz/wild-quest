package com.android.finalproject.screens.main.soloreview.scoretally

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.android.finalproject.R
import com.android.finalproject.screens.main.dashboard.DashboardActivity
import com.android.finalproject.utils.getLinearButtonView

class SoloScoreTallyActivity : AppCompatActivity(), SoloScoreTallyContract.View {
    private lateinit var presenter: SoloScoreTallyPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_solo_score_tally)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        presenter = SoloScoreTallyPresenter(this)
        presenter.displayScore(score, total)

        getLinearButtonView(R.id.homeBtn).setOnClickListener{
            navigateToHome()
        }
    }


    // score display
    override fun showScore(score: Int, total: Int){
        findViewById<TextView>(R.id.tv_score).text = "$score / $total"
    }


    // navigation
    override fun navigateToHome(){
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }


    // highlighting
    override fun highlightGood() {
        findViewById<TextView>(R.id.tv_score)
            .setTextColor(getColor(android.R.color.holo_green_dark))
        findViewById<TextView>(R.id.score_message)
            .setTextColor(getColor(android.R.color.holo_green_dark))
        findViewById<TextView>(R.id.score_message).setText("You are a Genius! Keep it up!!")
    }

    override fun highlightPass() {
        findViewById<TextView>(R.id.tv_score)
            .setTextColor(getColor(android.R.color.holo_blue_dark))
        findViewById<TextView>(R.id.score_message)
            .setTextColor(getColor(android.R.color.holo_blue_dark))
        findViewById<TextView>(R.id.score_message).setText("You Passed! Great Work!!")
    }

    override fun highlightFail() {
        findViewById<TextView>(R.id.tv_score)
            .setTextColor(getColor(android.R.color.holo_red_dark))
        findViewById<TextView>(R.id.score_message)
            .setTextColor(getColor(android.R.color.holo_red_dark))
        findViewById<TextView>(R.id.score_message).setText("You Failed. Let's Get it Next Time!!")
    }
}