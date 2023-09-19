package com.jefflete.crazyproducts.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jefflete.crazyproducts.core.R
import com.jefflete.crazyproducts.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val productId = intent.getIntExtra(EXTRA_DATA, 0)

        setData(productId)
    }

    private fun setData(productId: Int) {
        detailViewModel.product(productId).observe(this) { product ->
            binding?.apply {
                Glide.with(this@DetailActivity).load(product.thumbnail).into(imageView)
                tvName.text = product.title
                tvPrice.text = getString(R.string.product_price, product.price)
                tvDesc.text = product.description
                tvBrand.text = product.brand
                tvStock.text = product.stock.toString()
                tvRating.text = getString(R.string.product_rating, product.rating)
                when (product.isFavorite) {
                    true -> btnFavorite.setImageResource(R.drawable.baseline_favorite_24)
                    false -> btnFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                }
            }
            binding?.btnFavorite?.setOnClickListener {
                detailViewModel.setFavoriteProduct(
                    newStatus = !product.isFavorite, product = product
                )
                Log.d("DetailFragment", "product.isFavorite: ${product.isFavorite}")
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}