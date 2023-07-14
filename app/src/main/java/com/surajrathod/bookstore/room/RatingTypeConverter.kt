package com.surajrathod.bookstore.room

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.surajrathod.bookstore.model.Rating

class RatingTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromRating(rating: Rating) : String{
        return gson.toJson(rating)
    }

    @TypeConverter
    fun toRating(ratingJson : String) : Rating{
        return gson.fromJson(ratingJson,Rating::class.java)
    }
}