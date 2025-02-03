package com.divya.assignment.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.divya.assignment.NetWork.ProductRepository
import com.divya.assignment.Module.ProductResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {


    private val _productResponse = MutableLiveData<Response<ProductResponse>>()
    val productResponse: LiveData<Response<ProductResponse>> get() = _productResponse

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchProductDetails(productId: String, otherId: String) {
        viewModelScope.launch {
            val response = repository.getProductDetails(productId, otherId)
            if (response.isSuccessful) {
                _productResponse.postValue(response)
            } else {
                _error.postValue(response.errorBody()?.string())
            }
        }
    }
}
