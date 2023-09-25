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
        val title = findViewById<TextInputLayout>(R.id.til_title)
        val expirationDate = findViewById<TextInputLayout>(R.id.til_expirationDate)
        val expirationDateEditText = findViewById<TextInputEditText>(R.id.expirationDateEditText)
        val description = findViewById<TextInputLayout>(R.id.til_description)


        expirationDateEditText.setOnClickListener {
            Functions.showDatePickerDialog(this, expirationDateEditText)
        }
        btnAdd.setOnClickListener {
            var title = title.editText?.text.toString()
            var expiration = expirationDate.editText?.toString()
            var description = description.editText?.text.toString()


            Toast.makeText(this,title+" "+expirationDate+description, Toast.LENGTH_SHORT).show()
            val intent = Intent(this,GoalsActivity::class.java)

            //startActivity(intent)

        }
    }




}