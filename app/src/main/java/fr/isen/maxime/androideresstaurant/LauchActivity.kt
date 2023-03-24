package fr.isen.maxime.androideresstaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.maxime.androideresstaurant.databinding.ActivityLauchBinding
import org.json.JSONArray
import org.json.JSONObject

class LauchActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var binding: ActivityLauchBinding
    private lateinit var categoryTitle:  String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryTitle = intent.getStringExtra("menu") ?: ""

        val dishes = resources.getStringArray(R.array.dishes).toList() as ArrayList
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
        binding.RecyclerView.adapter = MenuAdapter(arrayListOf()){
            val intent = Intent(this, DetailActivity::class.java) //page de base vers nouvelle
            intent.putExtra("dish", it)//faire passer en parametre it=entre plat ou dessert selectionne
            startActivity(intent)//lancer la page
        }


        fetchMenuItems()

    }


    private fun fetchMenuItems() {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val requestBody = JSONObject().apply {
            put("id_shop", 1)
        }

        val request = JsonObjectRequest(Request.Method.POST, url, requestBody,
            { response ->
                Log.d("bla", response.toString())
                val data = Gson().fromJson(response.toString(), DataResult::class.java)
                val dishes = data.data.firstOrNull{it.nameFr == categoryTitle}?.items?.toList() as ArrayList //filtrer par categorie ici (a faire)
                (binding.RecyclerView.adapter as MenuAdapter).updateDishes(dishes)

            },
            { error ->
                Log.d("err", error.toString())
            })

        Volley.newRequestQueue(this).add(request)
    }







}