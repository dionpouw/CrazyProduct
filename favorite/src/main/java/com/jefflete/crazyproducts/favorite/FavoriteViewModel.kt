package com.jefflete.crazyproducts.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jefflete.crazyproducts.core.domain.usecase.ProductUseCase

class FavoriteViewModel(productUseCase: ProductUseCase) : ViewModel() {
    val favoriteProducts = productUseCase.getFavoriteProduct().asLiveData()
}
