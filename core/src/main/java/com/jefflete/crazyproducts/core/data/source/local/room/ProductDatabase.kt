package com.jefflete.crazyproducts.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jefflete.crazyproducts.core.data.source.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}