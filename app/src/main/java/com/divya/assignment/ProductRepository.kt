package com.divya.assignment

import retrofit2.Response

class ProductRepository {
    private val apiService = RetrofitInstance.apiService

    suspend fun getProductDetails(productId: String, otherId: String): Response<ProductResponse> {
        return apiService.getProductDetails(productId, otherId)
    }
}