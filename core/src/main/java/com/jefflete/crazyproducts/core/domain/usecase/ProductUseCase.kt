package com.jefflete.crazyproducts.core.domain.usecase

import com.jefflete.crazyproducts.core.data.Resource
import com.jefflete.crazyproducts.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun getAllProducts(): Flow<Resource<List<Product>>>
    fun getFavoriteProduct(): Flow<List<Product>>
    fun getProductsBySearch(productName: String): Flow<List<Product>>
    fun getProductById(productId: Int): Flow<Product>
    suspend fun setFavoriteProduct(product: Product, state: Boolean)
}