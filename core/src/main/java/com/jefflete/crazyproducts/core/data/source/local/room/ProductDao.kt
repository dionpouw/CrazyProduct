package com.jefflete.crazyproducts.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jefflete.crazyproducts.core.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product where productId = :id")
    fun getProductById(id: Int): Flow<ProductEntity>

    @Query("SELECT * FROM product where isFavorite = 1")
    fun getFavoriteProduct(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product where title LIKE '%' || :productName || '%'")
    fun getProductsBySearch(productName: String): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(tourism: List<ProductEntity>)

    @Update
    suspend fun updateFavoriteProduct(tourism: ProductEntity)
}