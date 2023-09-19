package com.jefflete.crazyproducts.core.data

import com.jefflete.crazyproducts.core.data.source.local.LocalDataSource
import com.jefflete.crazyproducts.core.data.source.remote.RemoteDataSource
import com.jefflete.crazyproducts.core.data.source.remote.network.ApiResponse
import com.jefflete.crazyproducts.core.data.source.remote.response.ProductsItem
import com.jefflete.crazyproducts.core.domain.model.Product
import com.jefflete.crazyproducts.core.domain.repository.IProductRepository
import com.jefflete.crazyproducts.core.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository(
    private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource
) : IProductRepository {

    override fun getAllProducts(): Flow<Resource<List<Product>>> =
        object : NetworkBoundResource<List<Product>, List<ProductsItem>>() {
            override fun loadFromDB(): Flow<List<Product>> =
                localDataSource.getAllProducts().map { Mapper.mapEntitiesToDomain(it) }

            override suspend fun createCall(): Flow<ApiResponse<List<ProductsItem>>> =
                remoteDataSource.getAllProducts()

            override suspend fun saveCallResult(data: List<ProductsItem>) =
                localDataSource.insertProducts(Mapper.mapResponsesToEntities(data))

            override fun shouldFetch(data: List<Product>?): Boolean = data.isNullOrEmpty()

        }.asFlow()

    override fun getFavoriteProduct(): Flow<List<Product>> =
        localDataSource.getFavoriteProduct().map { Mapper.mapEntitiesToDomain(it) }

    override fun getProductsBySearch(productName: String): Flow<List<Product>> =
        localDataSource.getProductBySearch(productName).map {
            Mapper.mapEntitiesToDomain(it)
        }

    override fun getProductById(productId: Int) = localDataSource.getProductById(productId).map {
        Mapper.mapEntitiesToDomain(it)
    }

    override suspend fun setFavoriteProduct(product: Product, state: Boolean) {
        localDataSource.setFavoriteProduct(Mapper.mapDomainToEntity(product), state)
    }

}