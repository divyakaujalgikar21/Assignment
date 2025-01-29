package com.divya.assignment

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModels { ProductViewModelFactory(ProductRepository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productViewModel.fetchProductDetails("6701", "253620")

//        productViewModel.productResponse.observe(this, Observer { response ->
//            if (response.isSuccessful) {
//                Log.d("product", "product name :${response.body()?.data?.name})}")
//            } else {
//                Log.e("API_ERROR", "Error: ${response.errorBody()}")
//            }
//        })
    }
}
