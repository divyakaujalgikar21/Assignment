package com.divya.assignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _productResponse = MutableLiveData<Response<ProductResponse>>()
    val productResponse: LiveData<Response<ProductResponse>> get() = _productResponse

    fun fetchProductDetails(productId: String, otherId: String) {
        viewModelScope.launch {
            val response = repository.getProductDetails(productId, otherId)
            _productResponse.postValue(response)
        }
    }
}
