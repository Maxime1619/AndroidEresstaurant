package fr.isen.maxime.androideresstaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {//activity represente les differentes pages
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClicked(view: View){
        println(view)
        when (view.id) {
            R.id.button_entree -> {
                // Action à effectuer pour le bouton "Entrée"
                println("Button Entree")
            }
            R.id.button_meal -> {
                // Action à effectuer pour le bouton "Plat"
                println("Button Meal")
            }
            R.id.button_dessert -> {
                // Action à effectuer pour le bouton "Dessert"
                println("Button Dessert")
            }
        }


    }


}