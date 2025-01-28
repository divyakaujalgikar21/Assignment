package com.divya.assignment

data class ProductResponse(
    val status: Int,
    val message: String,
    val data: ProductData
)

data class ProductData(
    val id: String,
    val sku: String,
    val name: String,
    val price: String,
    val final_price: String,
    val description: String?,
    val images: List<String>
)
