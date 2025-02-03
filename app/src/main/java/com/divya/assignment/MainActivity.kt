package com.divya.assignment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.divya.assignment.BindingAdapter.CarouselAdapter
import com.divya.assignment.NetWork.ProductRepository
import com.divya.assignment.ViewModel.ProductViewModel
import com.divya.assignment.ViewModel.ProductViewModelFactory
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
        val description = findViewById<WebView>(R.id.product_description)
        description.loadData(
            "<html><body><h1>HTML Content</h1></body></html>",
            "text/html",
            "UTF-8"
        )

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


    fun toggleDescription(view: View) {
        val description = findViewById<WebView>(R.id.product_description)
        val divider = findViewById<View>(R.id.divider)
        if (description.visibility == View.GONE) {
            description.visibility = View.VISIBLE
            divider.visibility = View.VISIBLE
        } else {
            description.visibility = View.GONE
            divider.visibility = View.GONE
        }
    }
}


@BindingAdapter("htmlContent")
fun loadHtmlContent(view: WebView, htmlContent: String?) {
    htmlContent?.let {
        view.loadData(it, "text/html", "UTF-8")
    }
}
