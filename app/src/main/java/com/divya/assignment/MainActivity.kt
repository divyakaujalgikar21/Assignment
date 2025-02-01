package com.divya.assignment

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.divya.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(
            ProductRepository()
        )
    }
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this  // Set lifecycle owner
        binding.product = null  // I

        // Observe LiveData
        productViewModel.productResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { product ->
                    binding.product = product  // Bind API response to UI
                    Log.d("product", "Product name: ${product.name}")
                }
            } else {
                Log.e("API_ERROR", "Error: ${response.errorBody()?.string()}")
            }
        })

        productViewModel.fetchProductDetails("6701", "253620")


    }
}
