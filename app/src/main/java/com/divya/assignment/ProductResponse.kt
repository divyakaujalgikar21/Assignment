package com.divya.assignment

import java.math.BigDecimal
import java.math.RoundingMode

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
) {
    fun getRoundedPrice(): String {
        val priceBigDecimal = BigDecimal(price)
        val roundedPrice = priceBigDecimal.setScale(2, RoundingMode.UP)
        return roundedPrice.toString()
    }
}