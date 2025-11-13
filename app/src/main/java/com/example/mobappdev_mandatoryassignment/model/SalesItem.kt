package com.example.mobappdev_mandatoryassignment.model

import java.time.LocalDateTime

data class SalesItem(
    val id: Int,
    val description: String,
    val price: Double,
    val sellerMail: String,
    val sellerPhone: String,
    val time: LocalDateTime,
//    val pictureUrl: String
) {
    constructor(
        description: String,
        price: Double,
        sellerMail: String,
        sellerPhone: String,
        time: LocalDateTime,
//        pictureUrl: String
    ) : this(
        id = -1, description, price, sellerMail, sellerPhone, time, /*pictureUrl*/
    )

    override fun toString(): String {
        return "$id, $description, $price, $sellerMail, $sellerPhone, $time, /*pictureUrl*/"
    }
}