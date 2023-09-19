package com.jefflete.crazyproducts.core.data.source.remote.network

import com.jefflete.crazyproducts.core.data.source.remote.response.ProductResponse
import retrofit2.http.GET

interface ApiService {
    @GET("product")
    suspend fun getList(): ProductResponse
}