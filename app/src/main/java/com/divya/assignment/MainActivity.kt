package com.divya.assignment

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fetch product details
        viewModel.fetchProductDetails("6701", "253620")

        // Observe LiveData
        viewModel.productResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d("API_RESPONSE", "Data: ${response.body()?.data}")
            } else {
                Log.e("API_ERROR", "Error: ${response.errorBody()}")
            }
        })
    }
}
