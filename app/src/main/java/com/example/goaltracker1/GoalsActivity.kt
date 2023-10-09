package com.example.goaltracker1

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class GoalsActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        val btnAdd = findViewById<FloatingActionButton>(R.id.floatActionButton)
        val btn_location = findViewById<FloatingActionButton>(R.id.btn_location)


        //Codigo Nuevo
        val lv_card = findViewById<RecyclerView>(R.id.lv_card)
        //poblar recyclerView
        lv_card.layoutManager = LinearLayoutManager(this)
        /*  val items = mutableListOf(
            Item("Elemento 1", "fecha 1", "descripcion 1","ëstado iniciando"),
            Item("Elemento 2", "fecha 2", "descripcion 2","ëstado comletado"),
            Item("Elemento 3", "fecha 3", "descripcion 3","ëstado no lo se")
            // Agrega más elementos según sea necesario
        )*/



        val adapter = CustomAdapter(AddgoalsActivity.goalsList) { item ->
            //Toast.makeText(this, "${item.title}, ${item.description}", Toast.LENGTH_SHORT).show()
            val position = AddgoalsActivity.goalsList.indexOf(item)
            val intent = Intent(this@GoalsActivity, DetailsActivity::class.java)
            intent.putExtra("goals_title", item.title)
            intent.putExtra("expirationDate", item.expiration)
            intent.putExtra("description", item.description)
            intent.putExtra("state", item.state)

            intent.putExtra("goals_id", position)// Pasa la posición del elemento
            startActivity(intent)
        }
        lv_card.adapter = adapter

        //IMPLEMENTAMOS GESTOS
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {return false}

            //IMPLEMENTAMOS DECORADOR CON LA LIBRERIA RecyclerViewSwipeDecorator
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@GoalsActivity, R.color.danger))
                    .addSwipeLeftActionIcon(R.drawable.baseline_delete_24)
                  /*  .addSwipeRightBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.archiveColor))
                    .addSwipeRightActionIcon(R.drawable.ic_archive)*/
                    .create()
                    .decorate()

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        adapter.removeItem(position)
                        Toast.makeText(this@GoalsActivity, "Elemento eliminado", Toast.LENGTH_SHORT).show()
                    }
                  /*  ItemTouchHelper.RIGHT -> {
                        adapter.removeItem(position)
                        Toast.makeText(this@GoalsActivity, "Elemento archivado", Toast.LENGTH_SHORT).show()
                    }*/
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(lv_card)


        btnAdd.setOnClickListener{
            val intent = Intent(this, AddgoalsActivity::class.java)
            startActivity(intent)
        }

        btn_location.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }


}



    // Crear un ArrayAdapter y configurarlo para el ListView
    /*   adapterList = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
            lv_card.adapter = adapterList
            // Notifica al adaptador que los datos han cambiado
            adapterList.notifyDataSetChanged()
            //ADAPTADOR CON UNA FILA
            adapterList = ArrayAdapter(
                this@GoalsActivity,
                R.layout.custom_list_item, R.id.textViewTitle, AddgoalsActivity.goalsList
            )
            lv_card.adapter = adapterList
*/

    //METODO CLICK LISTVIEW
    /*     lv_card.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    p0: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedGoal = AddgoalsActivity.goalsList[position]
                    val goalParts = selectedGoal.split("\n")
                    val selectedState = receivedGoal?.state
                    if (goalParts.size >= 3) {
                        val title = goalParts[0]
                        val expirationDate = goalParts[1]
                        val description = goalParts[2]

                        println("Título: $title")
                        println("Fecha de vencimiento: $expirationDate")
                        println("Descripción: $description")
                        println("state: $selectedState")
                        println(AddgoalsActivity.goalsList[position]) //me da el titulo
                        println(position)//me la da posicion o id

                        val intent = Intent(this@GoalsActivity, DetailsActivity::class.java)
                        // intent.putExtra("goals_title", "${AddgoalsActivity.goalsList[position]}")
                        intent.putExtra("goals_id", "${position}")

                        intent.putExtra("goals_title", title)
                        intent.putExtra("expirationDate", expirationDate)
                        intent.putExtra("description", description)
                        intent.putExtra("state", selectedState)


                        startActivity(intent)


                    }
                }
            }
        }*/


