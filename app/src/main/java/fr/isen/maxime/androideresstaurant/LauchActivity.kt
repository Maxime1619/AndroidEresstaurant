package fr.isen.maxime.androideresstaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LauchActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lauch)

        val menu = intent.getStringExtra("menu")

        val message = when (menu) {
            "entree" -> "Ceci est le menu entree"
            "meal" -> "Ceci est le menu meal"
            "dessert" -> "Ceci est le menu dessert"
            else -> "Menu non valide"
        }

        val textView = findViewById<TextView>(R.id.textView3)
        textView.text = message
    }




}