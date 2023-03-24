package fr.isen.maxime.androideresstaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    private lateinit var itemNameFromLauchActivity:  Items
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        itemNameFromLauchActivity = intent.getSerializableExtra("dish") as Items
        findViewById<TextView>(R.id.detailDishName).text = itemNameFromLauchActivity.nameFr
    }
}