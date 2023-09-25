package com.example.goaltracker1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GoalsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        val btnAdd = findViewById<FloatingActionButton>(R.id.floatActionButton)
        btnAdd.setOnClickListener{
            val intent = Intent(this,AddgoalsActivity::class.java)

            startActivity(intent)
        }
    }


}