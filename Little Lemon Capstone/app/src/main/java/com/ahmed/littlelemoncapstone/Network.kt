package com.ahmed.littlelemoncapstone

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData(
   @SerialName ("menu")
   val menu : List<MenuItemNetwork>
)


@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id : Int,
    @SerialName("title")
    val title : String,
    @SerialName("description")
    val description :  String,
    @SerialName("price")
    val price : Double,
    @SerialName("category")
    val category : String,
    @SerialName("image")
    val image : String
){
    fun toMenuItemRoom() = MenuItemRoom(
        id = id,
        title = title,
        description = description,
        price = price,
        category = category,
        image = image
    )
}
