package com.jefflete.crazyproducts.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.jefflete.crazyproducts.core.data.Resource
import com.jefflete.crazyproducts.core.ui.ProductAdapter
import com.jefflete.crazyproducts.core.ui.SpacingItemDecorator
import com.jefflete.crazyproducts.databinding.ActivityHomeBinding
import com.jefflete.crazyproducts.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val productAdapter = ProductAdapter()

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    homeViewModel.productsBySearch(query).observe(this@HomeActivity) {
                        productAdapter.setData(it)
                    }
                }
                Log.d("HomeActivity", "onQueryTextSubmit: $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    homeViewModel.products.observe(this@HomeActivity) {
                        productAdapter.setData(it.data)
                    }
                }
                Log.d("HomeActivity", "onQueryTextChange: $newText")
                return true
            }
        })

        val x = (resources.displayMetrics.density * 4).toInt() //converting dp to pixels

        binding?.recyclerView?.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(false)
            addItemDecoration(SpacingItemDecorator(x))
            adapter = productAdapter
        }

        productAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it.productId)
            startActivity(intent)
        }

        homeViewModel.products.observe(this) { products ->
            if (products != null) {
                when (products) {
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvNoData?.visibility = View.GONE
                        binding?.recyclerView?.visibility = View.VISIBLE
                        binding?.floatingActionButton?.visibility = View.VISIBLE
                        productAdapter.setData(products.data)
                    }

                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.recyclerView?.visibility = View.GONE
                        binding?.floatingActionButton?.visibility = View.GONE
                        binding?.tvNoData?.visibility = View.VISIBLE
                    }
                }
            }
        }
        binding?.floatingActionButton?.setOnClickListener {
            val uri = Uri.parse("crazyproducts://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}