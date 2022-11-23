package com.blackhtr.bookfinder.data

import com.google.gson.annotations.SerializedName

data class TotalData(
    @SerializedName("kind")         val kind:String = "",
    @SerializedName("totalItems")   val totalItems:Int = -1,
    @SerializedName("items")        val items:List<Item>
)

data class Item(
    @SerializedName("kind")         val kind:String = "",
    @SerializedName("id")           val id:String = "",
    @SerializedName("etag")         val etag:String = "",
    @SerializedName("volumeInfo")   val volumeInfo:VolumeInfo

)

data class VolumeInfo(
    @SerializedName("title")        val title:String = "",
    @SerializedName("subtitle")     val subtitle:String = "",
    @SerializedName("authors")      val authors:List<String>,
    @SerializedName("publisher")    val publisher:String = "",
    @SerializedName("publishedDate") val publishedDate:String = "",
    @SerializedName("description")  val description:String = "",
    @SerializedName("imageLinks")   val imageLinks:Map<String, String>,
    @SerializedName("previewLink")  val previewLink:String = "",
    @SerializedName("infoLink")     val infoLink:String = "",
    @SerializedName("canonicalVolumeLink") val canonicalVolumeLink:String = ""
)