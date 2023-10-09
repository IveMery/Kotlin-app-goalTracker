package com.example.goaltracker1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddgoalsActivity : AppCompatActivity() {


    companion object {

        val goalsList = mutableListOf<Item>()
    }
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
                // Obtener los valores ingresados
                val titleAdd = findViewById<TextInputLayout>(R.id.til_title)
                val expirationDateAdd = findViewById<TextInputLayout>(R.id.til_expirationDate)
                val descriptionAdd = findViewById<TextInputLayout>(R.id.til_description)
                val title = titleAdd.editText?.text.toString()
                val description = descriptionAdd.editText?.text.toString()
                val expiration = expirationDateAdd.editText?.text.toString()

                // Pasar el objeto Goal a la actividad de lista
                val intent = Intent(this,GoalsActivity::class.java)
                var state = "Iniciando..."
                if (title.isNotBlank() && expiration.isNotBlank() && description.isNotBlank()) {

                    val newItem = Item(title, expiration, description, state)
                    val intent = Intent()
                    intent.putExtra("item_data", newItem)
                    goalsList.add(newItem)

                }
                startActivity(intent)

                Toast.makeText(
                    this,
                    "Objetivo Agregado",
                    Toast.LENGTH_SHORT
                ).show()


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

