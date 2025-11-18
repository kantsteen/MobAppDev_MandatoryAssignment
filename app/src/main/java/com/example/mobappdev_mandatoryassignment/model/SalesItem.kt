package com.example.mobappdev_mandatoryassignment.model

data class SalesItem(
    val id: Int? = null,
    val description: String,
    val price: Int,
    val sellerEmail: String,
    val sellerPhone: String,
    val time: Long? =  System.currentTimeMillis()/1000 ?: null,
//    val pictureUrl: String
) {
    constructor(
        description: String,
        price: Int,
        sellerMail: String,
        sellerPhone: String,
        time: Long = System.currentTimeMillis()/1000,
//        pictureUrl: String
    ) : this(
        id = -1, description, price, sellerMail, sellerPhone, time, /*pictureUrl*/
    )

    override fun toString(): String {
        return "$id, $description, $price, $sellerEmail, $sellerPhone, $time, /*pictureUrl*/"
    }
}