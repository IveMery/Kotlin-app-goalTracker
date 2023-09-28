package com.example.goaltracker1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
class AddgoalsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addgoals)

        // Obteniedo referencias
        val btnAdd = findViewById<Button>(R.id.addButton)
        val expirationDateEditText = findViewById<TextInputEditText>(R.id.expirationDateEditText)


        expirationDateEditText.setOnClickListener {
            Functions.showDatePickerDialog(this, expirationDateEditText,true,false)
        }
        btnAdd.setOnClickListener {

        val isFormValid = isFormAddValid()

            if (isFormValid) {
                Toast.makeText(
                    this,
                    "Objetivo Agregado",
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

        fun isFormAddValid() : Boolean {
            val titleAdd = findViewById<TextInputLayout>(R.id.til_title)
            val expirationDateAdd = findViewById<TextInputLayout>(R.id.til_expirationDate)
            val descriptionAdd = findViewById<TextInputLayout>(R.id.til_description)
            var title = titleAdd.editText?.text.toString()
            var description = descriptionAdd.editText?.text.toString()
            var expiration = expirationDateAdd.editText?.text.toString()

            val validate = Validate()
            val isTitleValid = validate.isTextNotEmpty(title)
            val isDescriptionValid = validate.isTextNotEmpty(description)
            val isExpirationDateValid = validate.isTextNotEmpty(expiration)
            var valid = true


            if(!isTitleValid){
                titleAdd.error ="Este campo es requerido"
                valid= false
            }else {
                titleAdd.error =""
            }
            if(!isExpirationDateValid){
                expirationDateAdd.error ="Este campo es requerido"
                valid= false
            }else {
                expirationDateAdd.error =""
            }
            if(!isDescriptionValid){
                descriptionAdd.error = "Este campo es requerido"
                valid = false
            }else {
                descriptionAdd.error =""
            }

            return valid

        }
    }

