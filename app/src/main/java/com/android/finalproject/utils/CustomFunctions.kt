package com.android.finalproject.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.android.finalproject.R
import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.screens.sub.logopage.LogoPageActivity
import com.google.android.material.textfield.TextInputEditText

fun Activity.app(): WildQuestApp = application as WildQuestApp

fun Activity.start(toClass: Class<*>) {
    startActivity(Intent(this, toClass))
    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
}
fun Activity.loadScreen(current: Activity, next:Class<out Activity>, extras: Bundle? = null, flags: Int = 0){
    val intent = Intent(current, LogoPageActivity::class.java)
    intent.putExtra("NEXT_ACTIVITY", next.name)
    intent.putExtra("FLAGS",flags)
    if(extras != null){
        intent.putExtra("EXTRAS", extras)
    }
    current.startActivity(intent)
    current.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}
fun Activity.getButtonView(id: Int) = findViewById<Button>(id)
fun Activity.getImgButtonView(id: Int) = findViewById<ImageView>(id)
fun Activity.getLinearButtonView(id: Int) = findViewById<LinearLayout>(id)
fun Activity.getDrawerView(id: Int) = findViewById<DrawerLayout>(id)
fun Activity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
fun Activity.setTextViewText(id: Int, string: String) {
    findViewById<TextView>(id).setText("$string")
}
fun Activity.setEditTextText(id: Int, string: String){
    findViewById<EditText>(id).setText("$string")
}
fun Activity.getEditText(id: Int) = findViewById<EditText>(id).text.toString()
fun Activity.getTextInputEditText(id: Int) = findViewById<TextInputEditText>(id).text.toString()