package com.surajrathod.bookstore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajrathod.bookstore.model.Products
import com.surajrathod.bookstore.network.NetworkService
import com.surajrathod.bookstore.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val networkService: NetworkService) : ViewModel() {

    private val _products = MutableLiveData<Result<Products>>()
    val products : LiveData<Result<Products>> get() = _products
    var loading = MutableLiveData(false)

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
}