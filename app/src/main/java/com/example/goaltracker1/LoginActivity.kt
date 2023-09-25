package com.example.goaltracker1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Obteniedo referencias
        val btnLogin = findViewById<Button>(R.id.btnlogin)
        val btnPassword =findViewById<Button>(R.id.btnpassword)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val emailLoqin = findViewById<TextInputLayout>(R.id.til_email_login)
        val passwordLogin = findViewById<TextInputLayout>(R.id.til_password_login)

        // OnClickListener para el botón


        btnLogin.setOnClickListener {
            var email = emailLoqin.editText?.text.toString()
            var password = passwordLogin.editText?.text.toString()
            Toast.makeText(this,email+" "+password,Toast.LENGTH_SHORT).show()
            val intent = Intent(this,GoalsActivity::class.java)

            startActivity(intent)

        }


        btnPassword.setOnClickListener {
            // Crea un Intent para iniciar la actividad 'activity_goals'
            val intent = Intent(this,ResetpasswordActivity::class.java)

            // Inicia la actividad 'activity_goals'
            startActivity(intent)

        }

        tvForgotPassword.setOnClickListener {

            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}