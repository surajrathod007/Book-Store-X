package com.surajrathod.bookstore.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("select * from products_table")
    fun getAllProducts() : List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(product : List<ProductEntity>)

    @Query("select count(id) from products_table")
    fun countProducts() : Int

    @Query("select * from products_table")
    fun fetchAllProducts() : Flow<List<ProductEntity>>

    @Query("DELETE FROM products_table")
    suspend fun clearProducts()
}