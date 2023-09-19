package com.jefflete.crazyproducts.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jefflete.crazyproducts.core.domain.model.Product
import com.jefflete.crazyproducts.core.domain.usecase.ProductUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val productUseCase: ProductUseCase) : ViewModel() {

    fun setFavoriteProduct(product: Product, newStatus: Boolean) {
        viewModelScope.launch {
            productUseCase.setFavoriteProduct(product, newStatus)
        }
    }

    val product: (Int) -> LiveData<Product> = { productId ->
        productUseCase.getProductById(productId).asLiveData()
    }
}