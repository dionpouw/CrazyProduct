package com.jefflete.crazyproducts.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.jefflete.crazyproducts.core.ui.ProductAdapter
import com.jefflete.crazyproducts.core.ui.SpacingItemDecorator
import com.jefflete.crazyproducts.detail.DetailActivity
import com.jefflete.crazyproducts.di.favoriteModule
import com.jefflete.crazyproducts.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        loadKoinModules(favoriteModule)

        val productAdapter = ProductAdapter()
        val x = (resources.displayMetrics.density * 4).toInt() //converting dp to pixels

        binding?.recyclerView?.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            addItemDecoration(SpacingItemDecorator(x))
            adapter = productAdapter
        }

        productAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it.productId)
            startActivity(intent)
        }

        favoriteViewModel.favoriteProducts.observe(this) { products ->
            if (products.isNullOrEmpty()) {
                binding?.tvEmptydata?.visibility = View.VISIBLE
                binding?.recyclerView?.visibility = View.GONE
                Log.d("FavoriteActivity", "kosong: $products")
            } else {
                binding?.recyclerView?.visibility = View.VISIBLE
                Log.d("FavoriteActivity", "onCreate: $products")
                productAdapter.setData(products)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}