package com.divya.assignment

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.divya.assignment.databinding.ActivityMainBinding
import me.relex.circleindicator.CircleIndicator3

class MainActivity : AppCompatActivity() {

    private var quantity = 1
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(ProductRepository())
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val decrementButton = findViewById<Button>(R.id.minus)
        val incrementButton = findViewById<Button>(R.id.plus)
        val quantityButton = findViewById<TextView>(R.id.quantitytextview)

        decrementButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                quantityButton.text = quantity.toString()
            }
        }

        incrementButton.setOnClickListener {
            quantity++
            quantityButton.text = quantity.toString()
        }

        val imageCarousel: ViewPager2 = findViewById(R.id.imageCarousel)
        val circleIndicator: CircleIndicator3 = findViewById(R.id.circleIndicator)

        productViewModel.productResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.data?.let { product ->
                    binding.product = product

                    val images = product.images
                    val adapter = CarouselAdapter(images)
                    imageCarousel.adapter = adapter

                    circleIndicator.setViewPager(imageCarousel)

                    Log.d("product", "Product name: ${product.name}")
                }
            } else {
                Log.e("API_ERROR", "Error: ${response.errorBody()?.string()}")
            }
        })

        productViewModel.fetchProductDetails("6701", "253620")
    }
}
