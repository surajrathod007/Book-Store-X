package com.surajrathod.bookstore.room

import com.surajrathod.bookstore.model.Rating

data class ProductEntity(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)
