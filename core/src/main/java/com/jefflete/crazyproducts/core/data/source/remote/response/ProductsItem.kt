package com.jefflete.crazyproducts.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsItem(

    @field:SerializedName("id") val id: Int,

    @field:SerializedName("title") val title: String,

    @field:SerializedName("stock") val stock: Int,

    @field:SerializedName("brand") val brand: String,

    @field:SerializedName("thumbnail") val thumbnail: String,

    @field:SerializedName("price") val price: Int,

    @field:SerializedName("rating") val rating: Double,

    @field:SerializedName("description") val description: String,
) : Parcelable
