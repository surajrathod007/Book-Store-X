package com.surajrathod.bookstore.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext applicationContext: Context): ProductsDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ProductsDatabase::class.java,
            "product_database"
        ).build()
    }

    @Provides
    fun provideProductDao(productDatabase: ProductsDatabase): ProductDao {
        return productDatabase.productDao()
    }
}