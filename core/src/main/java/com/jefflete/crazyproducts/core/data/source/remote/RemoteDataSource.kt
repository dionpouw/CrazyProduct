package com.jefflete.crazyproducts.core.data.source.remote

import android.util.Log
import com.jefflete.crazyproducts.core.data.source.remote.network.ApiResponse
import com.jefflete.crazyproducts.core.data.source.remote.network.ApiService
import com.jefflete.crazyproducts.core.data.source.remote.response.ProductsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllProducts(): Flow<ApiResponse<List<ProductsItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.products
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.products))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
