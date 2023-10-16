package com.example.goaltracker1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import roomDatabase.Db
import roomDatabase.entity.User

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val btnRegister = findViewById<Button>(R.id.btnRegister)
         val birthDateEditText = findViewById<TextInputEditText>(R.id.et_birthDate)

        //inicializar db
       val room = Room.databaseBuilder(this, Db::class.java,"database-app").allowMainThreadQueries().build()

        birthDateEditText.setOnClickListener {
            Functions.showDatePickerDialog(this, birthDateEditText,false,true)
        }

        btnRegister.setOnClickListener {
            val isFormValid = isFormRegisterValid()

            if (isFormValid) {
                // Registro exitoso
                val emailRegister = findViewById<TextInputLayout>(R.id.til_email_register)
                val nameRegister = findViewById<TextInputLayout>(R.id.til_name_register)
                val birthDateRegister =findViewById<TextInputLayout>(R.id.til_birthDate_register)
                val passwordRegister = findViewById<TextInputLayout>(R.id.til_password_register)

                //obterner widgets para db

                var email = emailRegister.editText?.text.toString()
                var password = passwordRegister.editText?.text.toString()
                var birthDate = birthDateRegister.editText?.text.toString()
                var name = nameRegister.editText?.text.toString()

                //Crear objeto a insertar
                val userRegister = User(email,name,birthDate,password)
                lifecycleScope.launch{
                    val id = room.daoUser().addUser(userRegister)
                    if(id>0){
                        val intent = Intent(this@RegisterActivity, GoalsActivity::class.java)
                        intent.putExtra("name",name)
                        startActivity(intent)
                        Toast.makeText(this@RegisterActivity,"Usuario registrado exitosamente",Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(this@RegisterActivity,"No se pudo registrar usuario",Toast.LENGTH_LONG).show()
                    }
                }

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