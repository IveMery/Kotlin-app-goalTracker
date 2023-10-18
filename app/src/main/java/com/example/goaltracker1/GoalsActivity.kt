package com.example.goaltracker1

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.launch
import roomDatabase.Db

class GoalsActivity : AppCompatActivity() {
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)
        //inicializar db
        val room =
            Room.databaseBuilder(this, Db::class.java, "database-app").allowMainThreadQueries()
                .build()
        val btnAdd = findViewById<FloatingActionButton>(R.id.floatActionButton)
        val btn_location = findViewById<FloatingActionButton>(R.id.btn_location)
        val tv_username = findViewById<TextView>(R.id.saludouser)

        //Codigo Nuevo
        val lv_card = findViewById<RecyclerView>(R.id.lv_card)
        val mail: String = intent.getStringExtra("mail").toString()
        val name: String = intent.getStringExtra("name").toString()
        val emptyGoals = findViewById<TextView>(R.id.emptyGoals)
        val nameCapitalized = name.capitalize()
        tv_username.setText("Hola ${nameCapitalized}!!")

        //poblar recyclerView
        lv_card.layoutManager = LinearLayoutManager(this)
        val Items = mutableListOf<Item>()

        lifecycleScope.launch {
            var respuesta = room.daoGoals().getGoalByUser(mail)

            for (indice in respuesta.indices) {
                Items.add(
                    Item(
                        respuesta[indice].id.toInt(),
                        respuesta[indice].title.toString(),
                        respuesta[indice].expirationDate.toString(),
                        respuesta[indice].description.toString(),
                        respuesta[indice].state.toString()
                    )
                )
            }
        }

        if (Items.isNotEmpty()) {
            emptyGoals.visibility = View.GONE
            lv_card.visibility = View.VISIBLE
            adapter = CustomAdapter(Items) { item ->

                val intent = Intent(this@GoalsActivity, DetailsActivity::class.java)
                intent.putExtra("goalID", item.id).toString()
                intent.putExtra("goals_title", item.title)
                intent.putExtra("expirationDate", item.expiration)
                intent.putExtra("description", item.description)
                intent.putExtra("state", item.state)
                intent.putExtra("mail", mail)
                intent.putExtra("name", name)
                startActivity(intent)
            }

        lv_card.adapter = adapter

    }else
    {
        emptyGoals.setText("Agrega algunos objetivos...")
        emptyGoals.visibility = View.VISIBLE
        lv_card.visibility = View.GONE
    }

        //IMPLEMENTAMOS GESTOS
        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                //IMPLEMENTAMOS DECORADOR CON LA LIBRERIA RecyclerViewSwipeDecorator
                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    RecyclerViewSwipeDecorator.Builder(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                        .addSwipeLeftBackgroundColor(
                            ContextCompat.getColor(
                                this@GoalsActivity,
                                R.color.danger
                            )
                        )
                        .addSwipeLeftActionIcon(R.drawable.baseline_delete_24)
                        /*  .addSwipeRightBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.archiveColor))
                    .addSwipeRightActionIcon(R.drawable.ic_archive)*/
                        .create()
                        .decorate()

                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }


                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            val item = Items[position]
                            val itemId = item.id
                            room.daoGoals().deleteById(itemId.toLong())
                            adapter.removeItem(position)
                            Toast.makeText(
                                this@GoalsActivity,
                                "Elemento eliminado",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    }
                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(lv_card)


        btnAdd.setOnClickListener {
            val intent = Intent(this, AddgoalsActivity::class.java)
            intent.putExtra("mail", mail)

            intent.putExtra("name", name)
            startActivity(intent)
        }

        btn_location.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }

}


