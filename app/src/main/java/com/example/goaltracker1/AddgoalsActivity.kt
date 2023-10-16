package com.example.goaltracker1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import roomDatabase.Db
import roomDatabase.entity.Goals

class AddgoalsActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addgoals)
        //inicializar db
        val room = Room.databaseBuilder(this, Db::class.java,"database-app").allowMainThreadQueries().build()

        // Obteniedo referencias
        val btnAdd = findViewById<Button>(R.id.addButton)
        val expirationDateEditText = findViewById<TextInputEditText>(R.id.expirationDateEditText)


        expirationDateEditText.setOnClickListener {
            Functions.showDatePickerDialog(this, expirationDateEditText,true,false)
        }
        val mail: String = intent.getStringExtra("mail").toString()

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
                val sp_add = findViewById<Spinner>(R.id.sp_add)
                val sp_state = sp_add.selectedItem.toString()

                // Pasar el objeto Goal a la actividad de lista


                if (title.isNotBlank() && expiration.isNotBlank() && description.isNotBlank()) {

                    //crear el objeto
                    val goal = Goals(title, expiration, description, sp_state, mail)


                    lifecycleScope.launch {
                        //INSERTAMOS
                        val id = room.daoGoals().addGoals(goal)
                        //OPCIONAL VER INFORMACION EN EL LOG DE LA BD
                        var respuesta = room.daoGoals().getAllGoals()
                        for (elemento in respuesta) {
                            println(elemento.toString())
                        }
                        if (id > 0) {
                            Log.d("IDgoal", id.toString())
                            Toast.makeText(
                                this@AddgoalsActivity,
                                "Elemento registrado correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@AddgoalsActivity, GoalsActivity::class.java)
                            intent.putExtra("mail", mail)
                            Log.d("el user de intet el mail", mail)
                            startActivity(intent)
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

