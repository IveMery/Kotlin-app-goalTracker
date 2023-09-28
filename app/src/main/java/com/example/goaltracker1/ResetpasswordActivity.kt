package com.example.goaltracker1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class ResetpasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpassword)
        val btnReset = findViewById<Button>(R.id.btnResetPassword)
        val emailReset =findViewById<TextInputLayout>(R.id.til_resetEmail)

        btnReset.setOnClickListener {
            var email = emailReset.editText?.text.toString()

            Toast.makeText(this,email+" ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,GoalsActivity::class.java)
            val validate = Validate()
            val isEmailValid = validate.validateEmail(email)

            if(isEmailValid){
                Toast.makeText(this,email+"Si estas registrado en nuestra bases de datos te enviaremos un correo para que puedas ingresar", Toast.LENGTH_SHORT).show()
            }else{
                if (!isEmailValid) {
                    emailReset.error = "Contraseña no válida"
                    return@setOnClickListener
                }
            }
        }
    }
}