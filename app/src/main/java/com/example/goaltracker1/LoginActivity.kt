package com.example.goaltracker1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import roomDatabase.Db

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //inicializar db
        val room = Room.databaseBuilder(this, Db::class.java,"database-app").allowMainThreadQueries().build()

        val btnLogin = findViewById<Button>(R.id.btnlogin)
        val btnPassword =findViewById<Button>(R.id.btnpassword)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val preference = getSharedPreferences("datos", Context.MODE_PRIVATE)
        val sw_remember = findViewById<Switch>(R.id.sw_remember)
        val emailLoqin = findViewById<TextInputLayout>(R.id.til_email_login)

        val passwordLogin = findViewById<TextInputLayout>(R.id.til_password_login)
        emailLoqin.editText?.setText(preference.getString("mail",""))
        passwordLogin.editText?.setText(preference.getString("pass",""))

        btnLogin.setOnClickListener {
            val isFormValid = isFormLoginValid()

            if (isFormValid) {
                //Inicio Exitoso
                val mail = emailLoqin.editText?.text.toString()
                val pass = passwordLogin.editText?.text.toString()


                val editor = preference.edit()
                if(sw_remember.isChecked){
                    editor.putString("mail",mail)
                    editor.putString("pass",pass)

                }else{
                    editor.putString("mail","")
                    editor.putString("pass","")
                }
                editor.commit()

                intent.putExtra("mail",mail)
                intent.putExtra("pass",pass)

                //db login
                lifecycleScope.launch{
                    val response = room.daoUser().login(mail,pass)
                    if(response.size == 1) {
                        Toast.makeText(this@LoginActivity,"Login exitoso",Toast.LENGTH_LONG).show()
                        val intent = Intent(this@LoginActivity, GoalsActivity::class.java)
                        intent.putExtra("mail",mail)
                        startActivity(intent)

                    }
                    else {
                        Toast.makeText(this@LoginActivity,"Login fallido",Toast.LENGTH_LONG).show()}

                }

            } else {
                Toast.makeText(
                    this,
                    "Por favor, complete el formulario correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnPassword.setOnClickListener {
            val intent = Intent(this,ResetpasswordActivity::class.java)
            startActivity(intent)
        }

        tvForgotPassword.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    fun isFormLoginValid(): Boolean{
        // Obteniedo referencias

        val emailLoqin = findViewById<TextInputLayout>(R.id.til_email_login)
        val passwordLogin = findViewById<TextInputLayout>(R.id.til_password_login)
        var email = emailLoqin.editText?.text.toString()
        var password = passwordLogin.editText?.text.toString()

        val validate = Validate()
        val isEmailValid = validate.validateEmail(email)
        val isPasswordValid = validate.validatePassword(password)
        var valid = true
        if (!isEmailValid) {
            emailLoqin.error = "Correo electrónico no válido"
            valid = false

        }else{
            emailLoqin.error = ""
        }

        if (!isPasswordValid) {
            passwordLogin.error = "Contraseña no válida"
            valid = false

        }else{
            passwordLogin.error = ""
        }
        return valid
    }

}