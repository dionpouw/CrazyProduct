package com.jefflete.crazyproducts.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val productId: Int,
    val title: String,
    val stock: Int,
    val brand: String,
    val thumbnail: String,
    val price: Int,
    val rating: Double,
    val description: String,
    val isFavorite: Boolean
) : Parcelable
