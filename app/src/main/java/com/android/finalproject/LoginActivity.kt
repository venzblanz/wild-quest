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
import com.android.finalproject.R.id

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val et_email = findViewById<EditText>(R.id.login_edittext_email)
        val et_password = findViewById<EditText>(R.id.login_edittext_password)

        val login_button = findViewById<Button>(id.login_loginbutton)
        val logo_button = findViewById<ImageButton>(id.login_logobutton)
        val register_button = findViewById<Button>(id.login_registerbutton)

        logo_button.setOnClickListener{
            val intent = Intent(this, LogoPageActivity::class.java)
            startActivity(intent)
        }
        login_button.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()

            val get_user = UserStore.users.find{
                it.email == email && it.password == password
            }

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please enter username and password!",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(get_user != null){
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("username", get_user.username)
                intent.putExtra("password", get_user.password)
                intent.putExtra("email", get_user.email)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Wrong credentials", Toast.LENGTH_LONG).show()
            }
        }
        register_button.setOnClickListener {
            val intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}