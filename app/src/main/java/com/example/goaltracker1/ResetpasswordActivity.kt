package com.example.goaltracker1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ResetpasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpassword)
        val btnReset = findViewById<Button>(R.id.btnResetPassword)
        val emailReset =findViewById<TextInputLayout>(R.id.til_resetEmail)

        btnReset.setOnClickListener {
            var emailReset = emailReset.editText?.text.toString()

            Toast.makeText(this,emailReset+" ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,GoalsActivity::class.java)

            //startActivity(intent)

        }
    }
}