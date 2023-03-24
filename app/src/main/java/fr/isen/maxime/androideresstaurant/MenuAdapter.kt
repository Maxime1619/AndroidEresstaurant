package fr.isen.maxime.androideresstaurant

import android.animation.AnimatorListenerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class MenuAdapter(private var dishes: ArrayList<Items>, val onDishClickListener : (Items) -> Unit) :
    RecyclerView.Adapter<MenuAdapter.LauchViewHolder>() {

    class LauchViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val dishName = view.findViewById<TextView>(R.id.dishName)
        val dickpic = view.findViewById<ImageView>(R.id.dickPic)
        val dishPrice = view.findViewById<TextView>(R.id.dishPrice)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_cell,parent,false)
        return LauchViewHolder(view)
    }

    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder(holder: LauchViewHolder, position: Int) {
        //afficher le nom
        val dish = dishes[position]
        holder.dishName.text = dish.nameFr
        //afficher le prix
        val price = dish.prices.minByOrNull { it.price?.toDoubleOrNull()!! ?: Double.MAX_VALUE}
        holder.dishPrice.text = "€${price?.price}"
        //détecter le click
        holder.itemView.setOnClickListener {
            onDishClickListener(dish)
        }
        //ajout de l'image
        val imageURL = dishes[position].images.firstOrNull()
        if (!imageURL.isNullOrEmpty()){
            Picasso.get().load(imageURL).into(holder.dickpic)
        }
    }

    fun updateDishes(dishesFromAPI: ArrayList<Items>){
        dishes = dishesFromAPI
        notifyDataSetChanged()
    }

}
