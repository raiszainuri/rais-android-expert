package com.raisrz.rais_project.core.data.source.remote.network

import com.raisrz.rais_project.core.data.source.remote.responses.SportResponse
import com.raisrz.rais_project.core.data.source.remote.responses.SportsItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
   @GET("all_sports.php")
   suspend fun getList(): SportResponse
}