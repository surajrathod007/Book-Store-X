package com.surajrathod.bookstore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajrathod.bookstore.model.ProductItem
import com.surajrathod.bookstore.model.Products
import com.surajrathod.bookstore.network.NetworkService
import com.surajrathod.bookstore.repository.ProductRepository
import com.surajrathod.bookstore.room.ProductEntity
import com.surajrathod.bookstore.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<Result<List<ProductItem>>>()
    val products: LiveData<Result<List<ProductItem>>> get() = _products
    var loading = MutableLiveData(false)

    var _localProducts = MutableLiveData<List<ProductEntity>>()

    val msg = MutableLiveData<String>()

    private fun toProductItem(p:ProductEntity) : ProductItem{
        return ProductItem(
            category = p.category,
            description = p.description,
            id = p.id,
            image = p.image,
            rating = p.rating,
            title = p.title,
            price = p.price
        )
    }
    fun loadProducts() {
        loading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val r = repository.fetchProducts().map {
                toProductItem(it)
            }
            _products.postValue(Result.Success(r))
            loading.postValue(false)
        }
    }
    /*
    fun loadProducts(){
        loading.postValue(true)
        viewModelScope.launch {
            try{
                val r = networkService.loadProducts()
                _products.postValue(Result.Success(r))
            }catch (e : HttpException){
                _products.postValue(Result.Failure("Http Exception",e))
            }catch (e : IOException){
                _products.postValue(Result.Failure("Io Exception",e))
            }finally {
                loading.postValue(false)
            }
        }
    }

     */
}