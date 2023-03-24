package fr.isen.maxime.androideresstaurant

import com.google.gson.annotations.SerializedName


data class DataResult (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

)  : java.io.Serializable