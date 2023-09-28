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
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        val birthDateEditText = findViewById<TextInputEditText>(R.id.et_birthDate)

        birthDateEditText.setOnClickListener {
            Functions.showDatePickerDialog(this, birthDateEditText,false,true)
        }

        btnRegister.setOnClickListener {
            val isFormValid = isFormRegisterValid()

            if (isFormValid) {
                // Registro exitoso
                Toast.makeText(
                    this,
                    "Registro exitoso",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, GoalsActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Por favor, complete el formulario correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
    fun isFormRegisterValid(): Boolean {

        val emailRegister = findViewById<TextInputLayout>(R.id.til_email_register)
        val nameRegister = findViewById<TextInputLayout>(R.id.til_name_register)
        val birthDateRegister =findViewById<TextInputLayout>(R.id.til_birthDate_register)
        val passwordRegister = findViewById<TextInputLayout>(R.id.til_password_register)
        val rpasswordRegister = findViewById<TextInputLayout>(R.id.til_rpassword_register)


        var email = emailRegister.editText?.text.toString()
        var password = passwordRegister.editText?.text.toString()
        var birthDate = birthDateRegister.editText?.text.toString()
        var rpassword = rpasswordRegister.editText?.text.toString()
        var name = nameRegister.editText?.text.toString()

        val validate = Validate()
        val isEmailValid = validate.validateEmail(email)
        val isUserValid = validate.validateName(name)
        val isBirthDateValid = validate.isTextNotEmpty(birthDate)
        val isPasswordValid = validate.validatePassword(password)
        val isRPasswordValid = validate.validatePassword(rpassword)
        val isArefieldsEqual = validate.areFieldsEqual(password,rpassword)

        var valid = true
        if (!isEmailValid) {
            emailRegister.error ="Correo electrónico no válido"
            valid=false
        }else {
            emailRegister.error =""
        }

        if (isUserValid) {
             nameRegister.error ="Ingrese un nombre valido"
            valid =false
        }else{
            nameRegister.error =""
        }

        if (!isBirthDateValid) {
            birthDateRegister.error ="Por favor, seleccione su fecha de nacimiento"
            valid =false
        }else {
            birthDateRegister.error = ""
        }

        if (!isPasswordValid) {
            passwordRegister.error ="La contraseña no cumple con los requisitos mínimos"
            valid =false
        }else{
            if(!isArefieldsEqual) {
                passwordRegister.error = "Las contrasenas no coinciden"
                rpasswordRegister.error = "Las contrasenas no coinciden"
                valid = false
            }else{
                passwordRegister.error = ""
                rpasswordRegister.error = ""
            }
        }

        if(!isRPasswordValid) {
            rpasswordRegister.error = "La contraseña no cumple con los requisitos mínimos"
            valid = false
        }else{

                if(!isArefieldsEqual) {
                    passwordRegister.error = "Las contrasenas no coinciden"
                    rpasswordRegister.error = "Las contrasenas no coinciden"
                    valid = false
                }else{
                    passwordRegister.error = ""
                    rpasswordRegister.error = ""
                }
        }
        return valid
    }
}