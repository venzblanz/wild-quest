package com.android.finalproject.screens.sub.logopage

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.screens.main.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class LogoPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_logo_page)

        val randomTime = Random.nextLong(1000, 3000)

        lifecycleScope.launch {
            delay(randomTime)
            val nextActivity = intent.getStringExtra("NEXT_ACTIVITY")
            val flags = intent.getIntExtra("FLAGS",0)
            val extras = intent.getBundleExtra("EXTRAS")
            val nextClass = if(nextActivity != null) {
                Class.forName(nextActivity)
            }else{
                LoginActivity::class.java
            }
            val nextIntent = Intent(this@LogoPageActivity, nextClass)
            if(flags != 0){
                nextIntent.flags = flags
            }
            if(extras != null){
                nextIntent.putExtras(extras)
            }
            startActivity(nextIntent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }
}