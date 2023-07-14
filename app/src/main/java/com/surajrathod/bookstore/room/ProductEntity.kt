package com.surajrathod.bookstore.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.surajrathod.bookstore.model.Rating

@Entity("products_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)
