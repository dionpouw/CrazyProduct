package com.jefflete.crazyproducts.core.domain.usecase

import com.jefflete.crazyproducts.core.domain.model.Product
import com.jefflete.crazyproducts.core.domain.repository.IProductRepository

class ProductInteractor(private val productRepository: IProductRepository) : ProductUseCase {

    override fun getAllProducts() = productRepository.getAllProducts()

    override fun getFavoriteProduct() = productRepository.getFavoriteProduct()

    override fun getProductsBySearch(productName: String) =
        productRepository.getProductsBySearch(productName)

    override fun getProductById(productId: Int) = productRepository.getProductById(productId)

    override suspend fun setFavoriteProduct(product: Product, state: Boolean) =
        productRepository.setFavoriteProduct(product, state)
}