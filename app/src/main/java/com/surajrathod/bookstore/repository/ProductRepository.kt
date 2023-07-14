package com.surajrathod.bookstore.repository

import androidx.lifecycle.LiveData
import com.surajrathod.bookstore.model.ProductItem
import com.surajrathod.bookstore.model.Products
import com.surajrathod.bookstore.network.NetworkService
import com.surajrathod.bookstore.room.ProductDao
import com.surajrathod.bookstore.room.ProductEntity
import com.surajrathod.bookstore.utils.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val networkService: NetworkService,
    private val db: ProductDao
) {


    suspend fun fetchLocalProducts(): List<ProductEntity> {
        return db.getAllProducts()
    }

    suspend fun fetchProducts(): List<ProductEntity> {

        try {
            val r = networkService.loadProducts()
            val productEntities = r.map {
                ProductEntity(
                    category = it.category,
                    description = it.description,
                    image = it.image,
                    rating = it.rating,
                    price = it.price,
                    title = it.title, id = 0
                )
            }
            val count = db.countProducts()
            if (count < productEntities.size)
                db.insertProducts(productEntities)

            return db.getAllProducts()
        } catch (e: Exception) {
            /*
            if (!d.isNullOrEmpty()) {
                val data = d.map {
                    ProductItem(
                        category = it.category,
                        description = it.description,
                        id = it.id,
                        image = it.image,
                        price = it.price, rating = it.rating,
                        title = it.title
                    )
                }
                return Result.Success(data)
            }else{
                return Result.Failure("Can not load data from room ${e.localizedMessage}",e)
            }

             */
            return  db.getAllProducts()
        }

    }
}