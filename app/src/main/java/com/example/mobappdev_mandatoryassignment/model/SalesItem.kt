package com.example.mobappdev_mandatoryassignment.model

import java.time.LocalDateTime

data class SalesItem(
    val id: Int,
    val description: String,
    val price: Double,
    val sellerMail: String,
    val sellerPhone: String,
    val time: Long = System.currentTimeMillis()/1000,
//    val pictureUrl: String
) {
    constructor(
        description: String,
        price: Double,
        sellerMail: String,
        sellerPhone: String,
        time: Long = System.currentTimeMillis()/1000,
//        pictureUrl: String
    ) : this(
        id = -1, description, price, sellerMail, sellerPhone, time, /*pictureUrl*/
    )

    override fun toString(): String {
        return "$id, $description, $price, $sellerMail, $sellerPhone, $time, /*pictureUrl*/"
    }
}