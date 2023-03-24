package fr.isen.maxime.androideresstaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MenuAdapter(private var dishes: ArrayList<String>, val onDishClickListener : (String) -> Unit) :
    RecyclerView.Adapter<MenuAdapter.LauchViewHolder>() {

    class LauchViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val dishName = view.findViewById<TextView>(R.id.dishName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_cell,parent,false)
        return LauchViewHolder(view)
    }

    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder(holder: LauchViewHolder, position: Int) {
        val dish = dishes[position]
        holder.dishName.text = dish

        holder.itemView.setOnClickListener {
            onDishClickListener(dish)
        }
    }

    fun updateDishes(dishesFromAPI: ArrayList<String>){
        dishes = dishesFromAPI
        notifyDataSetChanged()
    }


}
