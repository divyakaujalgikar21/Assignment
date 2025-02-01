package com.divya.assignment

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("rest/V1/productdetails/{productId}/{otherId}?lang=en&store=KWD")
    suspend fun getProductDetails(
        @Path("productId") productId: String,
        @Path("otherId") otherId: String
    ): Response<ProductData>
}