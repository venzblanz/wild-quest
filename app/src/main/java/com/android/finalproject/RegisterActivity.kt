package com.android.finalproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        val et_username = findViewById<EditText>(R.id.register_edittext_username)
        val et_email = findViewById<EditText>(R.id.register_edittext_email)
        val et_password = findViewById<EditText>(R.id.register_edittext_password)
        val et_confirmpassword = findViewById<EditText>(R.id.register_edittext_confirmpassword)

        val confirm_button = findViewById<Button>(R.id.reg_confirmbutton)
        val logo_button = findViewById<ImageButton>(R.id.reg_logobutton)
        logo_button.setOnClickListener{
            val intent = Intent(this, LogoPageActivity::class.java)
            startActivity(intent)
        }
        confirm_button.setOnClickListener {
            val username = et_username.text.toString().trim()
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            val confirmpassword = et_confirmpassword.text.toString().trim()

            val intent = Intent (this, LoginActivity::class.java)
            if(username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()){
                Toast.makeText(this,"Please fill all required", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(password != confirmpassword){
                Toast.makeText(this,"Passwords doesn't match!",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val newUser = User(username,password,email)
            UserStore.users.add(newUser)
            Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}