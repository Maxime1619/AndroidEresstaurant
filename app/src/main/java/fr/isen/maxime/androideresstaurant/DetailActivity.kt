package fr.isen.maxime.androideresstaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
        /*val viewPager = findViewById<ViewPager>(R.id.viewPager2)
        val adapter = ImagePagerAdapter(supportFragmentManager, itemFromLauchActivity.images)
        viewPager.adapter = adapter*/

    }

    fun calculAndPrint(){
        totalPrice = itemCounter*price
        findViewById<TextView>(R.id.quantity).text = itemCounter.toString()
        findViewById<Button>(R.id.addToCard).text = "Add to card : " + totalPrice.toString() + "€"
    }


}

//ViewPager en cours d'écriture
/*class ImagePagerAdapter(fm: FragmentManager, private val images: List<String>) : FragmentPagerAdapter(fm) {
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
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        val imageUrl = arguments?.getString(ARG_IMAGE_URL)
        if(imageUrl!=null && imageUrl.isNotEmpty()){
            Picasso.get().load(imageUrl).into(imageView)}
        return imageView
    }


}*/