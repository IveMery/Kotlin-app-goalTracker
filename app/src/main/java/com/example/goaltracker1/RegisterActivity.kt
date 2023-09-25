package com.example.goaltracker1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Obteniedo referencias
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val emailRegister = findViewById<TextInputLayout>(R.id.til_email_register)
        val nameRegister = findViewById<TextInputLayout>(R.id.til_name_register)
       // val birthDateRegister =findViewById<TextInputLayout>(R.id.til_birthDate_register)
        val passwordRegister = findViewById<TextInputLayout>(R.id.til_password_register)
        val rpasswordRegister = findViewById<TextInputLayout>(R.id.til_rpassword_register)

        val birthDateEditText = findViewById<TextInputEditText>(R.id.et_birthDate)

        birthDateEditText.setOnClickListener {
            Functions.showDatePickerDialog(this, birthDateEditText)
        }

        btnRegister.setOnClickListener {
            var email = emailRegister.editText?.text.toString()
            var password = passwordRegister.editText?.text.toString()
           // var birthDate = birthDateRegister.editText?.text.toString()
            var rpassword = rpasswordRegister.editText?.text.toString()
            var name = nameRegister.editText?.text.toString()

            Toast.makeText(this,email+" "+password+name+rpassword, Toast.LENGTH_SHORT).show()
            val intent = Intent(this,GoalsActivity::class.java)

            startActivity(intent)


        }



    }



}