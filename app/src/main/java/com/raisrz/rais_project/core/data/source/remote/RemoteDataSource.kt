package com.raisrz.rais_project.core.data.source.remote

import android.util.Log
import com.raisrz.rais_project.core.data.source.remote.network.ApiResponse
import com.raisrz.rais_project.core.data.source.remote.network.ApiService
import com.raisrz.rais_project.core.data.source.remote.responses.SportsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    /*companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }*/
    suspend fun getAllSports(): Flow<ApiResponse<List<SportsItem>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.sports
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.sports))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    /*fun getAllSports(): LiveData<ApiResponse<List<SportsItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<SportsItem>>>()

        //get data from remote api
        val client = apiService.getList()
        client.enqueue(object : Callback<SportResponse> {
            override fun onResponse(
                call: Call<SportResponse>,
                response: Response<SportResponse>
            ) {
                val dataArray = response.body()?.sports
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }
            override fun onFailure(call: Call<SportResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }*/
}

