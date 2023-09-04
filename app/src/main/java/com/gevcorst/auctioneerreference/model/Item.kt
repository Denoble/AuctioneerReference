package com.gevcorst.auctioneerreference.model

import com.google.firebase.firestore.DocumentId

data class Item(
    @DocumentId val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val image: String = "",
    val barcode:String =""
)