package com.jefflete.crazyproducts.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResponse(

    @field:SerializedName("products") val products: List<ProductsItem>
) : Parcelable
