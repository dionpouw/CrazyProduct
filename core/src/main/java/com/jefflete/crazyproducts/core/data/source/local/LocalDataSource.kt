package com.jefflete.crazyproducts.core.data.source.local

import com.jefflete.crazyproducts.core.data.source.local.entity.ProductEntity
import com.jefflete.crazyproducts.core.data.source.local.room.ProductDao

class LocalDataSource(private val productDao: ProductDao) {

    fun getAllProducts() = productDao.getAllProducts()

    fun getFavoriteProduct() = productDao.getFavoriteProduct()

    fun getProductById(productId: Int) = productDao.getProductById(productId)

    fun getProductBySearch(productName: String) = productDao.getProductsBySearch(productName)

    suspend fun insertProducts(productList: List<ProductEntity>) =
        productDao.insertProducts(productList)

    suspend fun setFavoriteProduct(product: ProductEntity, newState: Boolean) {
        product.isFavorite = newState
        productDao.updateFavoriteProduct(product)
    }
}