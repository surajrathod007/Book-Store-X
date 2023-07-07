package com.surajrathod.bookstore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajrathod.bookstore.model.Products
import com.surajrathod.bookstore.network.NetworkService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val networkService: NetworkService) : ViewModel() {

    private val _products = MutableLiveData<Products>()
    val products : LiveData<Products> get() = _products

    fun loadProducts(){
        viewModelScope.launch {
            val r = networkService.loadProducts()
            _products.postValue(r)
        }
    }
}