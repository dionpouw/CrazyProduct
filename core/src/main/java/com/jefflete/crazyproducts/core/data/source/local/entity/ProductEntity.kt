package com.jefflete.crazyproducts.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product")
data class ProductEntity(

    @PrimaryKey @ColumnInfo(name = "productId") val productId: Int,

    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "stock") val stock: Int,

    @ColumnInfo(name = "brand") val brand: String,

    @ColumnInfo(name = "thumbnail") val thumbnail: String,

    @ColumnInfo(name = "price") val price: Int,

    @ColumnInfo(name = "rating") val rating: Double,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false

) : Parcelable
