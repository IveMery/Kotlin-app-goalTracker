package com.example.goaltracker1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import roomDatabase.Db

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        //inicializar db
        val room =
            Room.databaseBuilder(this, Db::class.java, "database-app").allowMainThreadQueries()
                .build()

        val btnDelete = findViewById<ImageButton>(R.id.btnDelete)
        val btnEdit = findViewById<ImageButton>(R.id.btnEdit)
        val til_title_details = findViewById<TextInputLayout>(R.id.til_title_details)
        val til_expirationDate_details =
            findViewById<TextInputLayout>(R.id.til_expirationDate_details)
        val til_description_details = findViewById<TextInputLayout>(R.id.til_description_details)

        val sp_details = findViewById<Spinner>(R.id.sp_details)


        val expirationDateEditText_details =
            findViewById<TextInputEditText>(R.id.expirationDateEditText_details)
        //funcion datepicker
        expirationDateEditText_details.setOnClickListener {
            Functions.showDatePickerDialog(this, expirationDateEditText_details, true, false)
        }

        //GENERACION DEL SPINNER
        val arrayAdapterSpinner: ArrayAdapter<*>
        var estados = ArrayList<String>()
        estados.add("Iniciando 😊")
        estados.add("En proceso 🙄")
        estados.add("Completado 😁")
        estados.add("No lo sé Rick 😢")
        arrayAdapterSpinner = ArrayAdapter(
            this@DetailsActivity,
            android.R.layout.simple_spinner_dropdown_item, estados
        )
        sp_details.adapter = arrayAdapterSpinner

        //obetner el valor
        var goalId = intent.getIntExtra("goalID", 0).toString()
        var goals_title = intent.getStringExtra("goals_title").toString()
        var goals_expirationDate = intent.getStringExtra("expirationDate").toString()
        var goals_description = intent.getStringExtra("description").toString()
        var goals_state = intent.getStringExtra("state").toString()
        val indiceSeleccionado = estados.indexOf(goals_state)

        til_title_details.editText?.setText(goals_title)
        til_expirationDate_details.editText?.setText(goals_expirationDate)
        til_description_details.editText?.setText(goals_description)
        sp_details.setSelection(indiceSeleccionado)

        val mail:String = intent.getStringExtra("mail").toString()
        val name:String = intent.getStringExtra("name").toString()

        btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("ELIMINAR")
            builder.setMessage("Desea eliminar este objetivo?")
            builder.setPositiveButton(android.R.string.ok) { dialog, which ->

                lifecycleScope.launch {
                    var id = goalId
                    val respuesta = room.daoGoals().deleteById(id.toLong())
                    println(respuesta)
                    val intent = Intent(this@DetailsActivity, GoalsActivity::class.java)
                    intent.putExtra("mail", mail)

                    intent.putExtra("name",name)
                    startActivity(intent)

                }
                Toast.makeText(this, "Elemento Eliminado", Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton("Cancelar", null)
            builder.show()


        }

        btnEdit.setOnClickListener {
            lifecycleScope.launch {
                var title = til_title_details.editText?.text.toString()
                var expirationDate = til_expirationDate_details.editText?.text.toString()
                var description = til_description_details.editText?.text.toString()
                var id = goalId
                var state = sp_details.selectedItem.toString()

                val respuesta = room.daoGoals().updateGoals(title,expirationDate,description,state,id.toLong())
                println(respuesta)//OPCIONAL
                val intent = Intent(this@DetailsActivity,GoalsActivity::class.java)
                intent.putExtra("mail",mail)
                intent.putExtra("name",name)
                startActivity(intent)
            }


        }

    }
}

