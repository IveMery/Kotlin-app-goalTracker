package com.example.goaltracker1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val btnDelete =findViewById<ImageButton>(R.id.btnDelete)
        val btnEdit =findViewById<ImageButton>(R.id.btnEdit)
        val til_title_details = findViewById<TextInputLayout>(R.id.til_title_details)
        val til_expirationDate_details = findViewById<TextInputLayout>(R.id.til_expirationDate_details)
        val til_description_details = findViewById<TextInputLayout>(R.id.til_description_details)


        //obetner el valor

        var goals_title:String = intent.getStringExtra("goals_title").toString()
        var goals_expirationDate:String = intent.getStringExtra("expirationDate").toString()
        var goals_description:String = intent.getStringExtra("description").toString()
        til_title_details.editText?.setText(goals_title)
        til_expirationDate_details.editText?.setText(goals_expirationDate)
        til_description_details.editText?.setText(goals_description)
        //obteniendo valor del intent
        var goals_selectedState = intent.getStringExtra("state")

        val expirationDateEditText_details = findViewById<TextInputEditText>(R.id.expirationDateEditText_details)
        //funcion datepicker
        expirationDateEditText_details.setOnClickListener {
            Functions.showDatePickerDialog(this, expirationDateEditText_details,true,false)
        }


        val sp_details = findViewById<Spinner>(R.id.sp_details)

        //GENERACION DEL SPINNER
        val arrayAdapterSpinner: ArrayAdapter<*>
        var estados = ArrayList<String>()
        estados.add("Iniciando 😊")
        estados.add("En proceso 🙄")
        estados.add("Completado 😁")
        estados.add("No lo sé Rick 😢")
        arrayAdapterSpinner = ArrayAdapter(this@DetailsActivity,
            android.R.layout.simple_spinner_dropdown_item,estados)
        sp_details.adapter = arrayAdapterSpinner

    //obtener el id de intent new

        btnDelete.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("ELIMINAR")
            builder.setMessage("Desea eliminar este objetivo?")
            builder.setPositiveButton(android.R.string.ok){
                    dialog, which ->

                val goal_id = intent.getIntExtra("goals_id",-1)
                Toast.makeText(this, "desde detalels ${goal_id}", Toast.LENGTH_SHORT).show()
                if (goal_id != null) {
                    val position = goal_id

                    if (position != null && position >= 0 && position < AddgoalsActivity.goalsList.size) {
                        AddgoalsActivity.goalsList.removeAt(position)
                        Toast.makeText(this@DetailsActivity, "Objetivo Eliminado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@DetailsActivity, "No se pudo eliminar el objetivo", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@DetailsActivity, "ID de objetivo no encontrado", Toast.LENGTH_SHORT).show()
                }

                val intent = Intent(this@DetailsActivity,GoalsActivity::class.java)
                (startActivity(intent))
            }
            builder.setNeutralButton("Cancelar",null)
            builder.show()
        }
            btnEdit.setOnClickListener{
                // Obtener los nuevos valores
                val newTitle = til_title_details.editText?.text.toString()
                val newExpirationDate = til_expirationDate_details.editText?.text.toString()
                val newDescription = til_description_details.editText?.text.toString()
                var selectedState = sp_details.selectedItem.toString()

                //obetener datos del spinner
                Toast.makeText(this@DetailsActivity, selectedState, Toast.LENGTH_SHORT).show()
                val initialSelectedPosition = sp_details.selectedItemPosition

                println("actualizando")
                println("posicion para actualizar $initialSelectedPosition")
                // Actualizar los valores de las variables
                goals_title = newTitle
                goals_expirationDate = newExpirationDate
                goals_description = newDescription
                goals_selectedState = selectedState

                // Actualizar los textos de los TextInputEditText
                til_title_details.editText?.setText(goals_title)
                til_expirationDate_details.editText?.setText(goals_expirationDate)
                til_description_details.editText?.setText(goals_description)
                sp_details.setSelection(initialSelectedPosition)

                // Actualizar los valores en AddgoalsActivity.goalsList
                               val goalId = intent.getIntExtra("goals_id",-1)
                if (goalId != null && goalId >= 0 && goalId < AddgoalsActivity.goalsList.size) {
                    val editedGoal = Item(goals_title, goals_expirationDate, goals_description, selectedState)
                    AddgoalsActivity.goalsList[goalId] = editedGoal
                }

                Toast.makeText(this@DetailsActivity, "Cambios guardados con éxito", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@DetailsActivity,GoalsActivity::class.java)
                (startActivity(intent))
            }
    }
}

