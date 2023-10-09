package com.example.goaltracker1


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView




class CustomAdapter( var items: MutableList<Item>, private val clickListener: (Item) -> Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, clickListener)
    }

    override fun getItemCount() = items.size

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Item, clickListener: (Item) -> Unit) {
            //val imagen = view.findViewById<ImageView>(R.id.imv)
            val tv_title = view.findViewById<TextView>(R.id.tv_cst1)
            val tv_expiration_date = view.findViewById<TextView>(R.id.tv_cst2)
            val tv_description = view.findViewById<TextView>(R.id.tv_cst3)
            val tv_state = view.findViewById<TextView>(R.id.tv_cst4)


            //imagen.setImageResource(item.imageResId)
            tv_title.text = item.title
            tv_expiration_date.text = item.expiration
            tv_description.text = item.description
            tv_state.text = item.state
            view.setOnClickListener { clickListener(item) }
        }
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }


}