package com.jefflete.crazyproducts.core.utils

import com.jefflete.crazyproducts.core.data.source.local.entity.ProductEntity
import com.jefflete.crazyproducts.core.data.source.remote.response.ProductsItem
import com.jefflete.crazyproducts.core.domain.model.Product

object Mapper {
    fun mapResponsesToEntities(input: List<ProductsItem>): List<ProductEntity> {
        val productList = ArrayList<ProductEntity>()
        input.map {
            val product = ProductEntity(
                productId = it.id,
                title = it.title,
                stock = it.stock,
                brand = it.brand,
                thumbnail = it.thumbnail,
                price = it.price,
                rating = it.rating,
                description = it.description,
                isFavorite = false
            )
            productList.add(product)
        }
        return productList
    }

    fun mapEntitiesToDomain(input: List<ProductEntity>): List<Product> = input.map {
        Product(
            productId = it.productId,
            title = it.title,
            stock = it.stock,
            brand = it.brand,
            thumbnail = it.thumbnail,
            price = it.price,
            rating = it.rating,
            description = it.description,
            isFavorite = it.isFavorite
        )
    }

    fun mapEntitiesToDomain(input: ProductEntity): Product {
        return Product(
            productId = input.productId,
            title = input.title,
            stock = input.stock,
            brand = input.brand,
            thumbnail = input.thumbnail,
            price = input.price,
            rating = input.rating,
            description = input.description,
            isFavorite = input.isFavorite
        )
    }

    fun mapDomainToEntity(input: Product) = ProductEntity(
        productId = input.productId,
        title = input.title,
        stock = input.stock,
        brand = input.brand,
        thumbnail = input.thumbnail,
        price = input.price,
        rating = input.rating,
        description = input.description,
        isFavorite = input.isFavorite
    )
}