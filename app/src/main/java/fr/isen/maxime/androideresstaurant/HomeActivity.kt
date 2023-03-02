package fr.isen.maxime.androideresstaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import fr.isen.maxime.androideresstaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {//activity represente les differentes pages

    private lateinit var binding: ActivityHomeBinding//declaration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)//init
        setContentView(binding.root)//utilisation

        //Click listener
        binding.buttonEntree.setOnClickListener {
            Log.d("MainActivity","button_entree")//même chose que println
            val intent = Intent(this, LauchActivity::class.java) //page de base vers nouvelle
            intent.putExtra("menu", "entree")//faire passer en parametre
            startActivity(intent)//lancer la page
            Toast.makeText(this, "Your are in the Entree menu", Toast.LENGTH_SHORT).show()
        }

        binding.buttonMeal.setOnClickListener {
            val intent = Intent(this, LauchActivity::class.java)
            intent.putExtra("menu", "meal")
            startActivity(intent)
            Toast.makeText(this, "Your are in the Meal menu", Toast.LENGTH_SHORT).show()
        }

        binding.buttonDessert.setOnClickListener {
            val intent = Intent(this, LauchActivity::class.java)
            intent.putExtra("menu", "dessert")
            startActivity(intent)
            Toast.makeText(this, "Your are in the Dessert menu", Toast.LENGTH_SHORT).show()
        }
    }




    /*fun buttonClicked(view: View){



        when (view.id) {
            R.id.button_entree -> {
                val intent = Intent(this, LauchActivity::class.java)
                intent.putExtra("menu", "entree")
                startActivity(intent)
                Toast.makeText(this, "Your are in the Entree menu", Toast.LENGTH_SHORT).show()
            }
            R.id.button_meal -> {
                val intent = Intent(this, LauchActivity::class.java)
                intent.putExtra("menu", "meal")
                startActivity(intent)
                Toast.makeText(this, "Your are in the Meal menu", Toast.LENGTH_SHORT).show()
            }
            R.id.button_dessert -> {
                val intent = Intent(this, LauchActivity::class.java)
                intent.putExtra("menu", "dessert")
                startActivity(intent)
                Toast.makeText(this, "Your are in the Dessert menu", Toast.LENGTH_SHORT).show()
            }
        }


    }*/


}