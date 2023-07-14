package com.surajrathod.bookstore.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(RatingTypeConverter::class)
abstract class ProductsDatabase : RoomDatabase() {
    //abstract val productDao: ProductDao
    abstract fun productDao() : ProductDao
    /*
    private lateinit var INSTANCE: ProductsDatabase

    fun getDatabase(context: Context): ProductsDatabase {
        if (!::INSTANCE.isInitialized) {
            INSTANCE =
                Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "product_database"
                )
                    .fallbackToDestructiveMigration().build()

        }
        return INSTANCE
    }

     */
}