package fr.isen.maxime.androideresstaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var itemFromLauchActivity:  Items
    private  var totalPrice : Float = 0F
    private  var itemCounter : Int = 1
    private  var price : Float = 0F
    private  var cardNb : Int = 0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Récupération depuis LauchActivity
        itemFromLauchActivity = intent.getSerializableExtra("dish") as Items
        price = (itemFromLauchActivity.prices[0].price ?: "0").toFloat()

        //Affiche nom item
        findViewById<TextView>(R.id.detailDishName).text = itemFromLauchActivity.nameFr
        findViewById<TextView>(R.id.quantity).text = itemCounter.toString()
        calculAndPrint()

        //Fait -1 sur le nb d'item
        findViewById<Button>(R.id.button_m).setOnClickListener {
            if (itemCounter > 0){
                itemCounter--
                calculAndPrint()
            }
        }

        //Fait +1 sur le nb d'item
        findViewById<Button>(R.id.button_p).setOnClickListener {
            itemCounter++
            calculAndPrint()
        }

        findViewById<Button>(R.id.addToCard).setOnClickListener {
            cardNb += itemCounter
            findViewById<TextView>(R.id.cardNumber).text = cardNb.toString()

            if(itemCounter != 0) {

                val message =
                    "$itemCounter x ${itemFromLauchActivity.nameFr} ajouté(s) au panier. Total: $totalPrice €"
                AlertDialog.Builder(this)
                    .setTitle("Ajout au panier")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }else {
                val message =
                    "Aucun plat à ajouter !"
                AlertDialog.Builder(this)
                    .setTitle("Ajout au panier")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }
    }


        //Construit la liste d'ingrédients
        val listeIngredients = itemFromLauchActivity.ingredients
        val construction = StringBuilder()
        for (i in listeIngredients.indices) {
            construction.append(listeIngredients[i].nameFr)
            if (i != listeIngredients.size - 1) {
                construction.append(", ")
            }
        }
        findViewById<TextView>(R.id.ingredients).text = construction.toString()

        //ViewPager en cours d'écriture
        val viewPager = findViewById<ViewPager>(R.id.imagePager)
        val adapter = ImagePagerAdapter(supportFragmentManager, itemFromLauchActivity.images)
        viewPager.adapter = adapter

    }

    fun calculAndPrint(){

        totalPrice = itemCounter*price
        findViewById<TextView>(R.id.quantity).text = itemCounter.toString()
        findViewById<TextView>(R.id.cardNumber).text = cardNb.toString()
        findViewById<Button>(R.id.addToCard).text = "Add to card : " + totalPrice.toString() + "€"
    }

    //ViewPager en cours d'écriture
    class ImagePagerAdapter(fm: FragmentManager, private val images: List<String>) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = images.size
        override fun getItem(position: Int): Fragment {
            return ImageFragment.newInstance(images[position])
        }
    }
    class ImageFragment : Fragment() {
        companion object {
            private const val ARG_IMAGE_URL = "image_url"

            fun newInstance(imageUrl: String): ImageFragment {
                val args = Bundle()
                args.putString(ARG_IMAGE_URL, imageUrl)
                val fragment = ImageFragment()
                fragment.arguments = args
                return fragment
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.image_fragment, container,false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val imageUrl = arguments?.getString(ARG_IMAGE_URL)
            val imageView = view.findViewById<ImageView>(R.id.fragmentImage)

            if (imageUrl != null && imageUrl.isNotEmpty()) {
                Picasso.get()
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .error(R.drawable.light)
                    .into(imageView)
            } else {
                imageView.setImageResource(R.drawable.light)
            }
        }

    }


}

