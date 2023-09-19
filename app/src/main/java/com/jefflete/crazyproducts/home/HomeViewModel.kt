package com.jefflete.crazyproducts.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jefflete.crazyproducts.core.domain.model.Product
import com.jefflete.crazyproducts.core.domain.usecase.ProductUseCase

class HomeViewModel(productUseCase: ProductUseCase) : ViewModel() {

    val products = productUseCase.getAllProducts().asLiveData()

    val productsBySearch: (String) -> LiveData<List<Product>> = { productName ->
        productUseCase.getProductsBySearch(productName).asLiveData()
    }

}