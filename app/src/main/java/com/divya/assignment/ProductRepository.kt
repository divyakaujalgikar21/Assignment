package com.divya.assignment

import android.util.Log
import retrofit2.Response

class ProductRepository {

    suspend fun getProductDetails(productId: String, otherId: String): Response<ProductData> {
        return try {
            val response = RetrofitInstance.apiService.getProductDetails(productId, otherId)

            if (response.isSuccessful) {
                Log.d("API_RESPONSE", "Success : ${response.body()}")
            } else {
                Log.e("API_ERROR", "Error: ${response.errorBody()}")

            }
            response
        } catch (e: Exception) {
            Log.e("API_ERROR", "Exception: ${e.message}")
            Response.error(500, okhttp3.ResponseBody.create(null, "Something went wrong"))
        }
    }
}