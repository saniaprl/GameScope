package com.responsi.gamescope.network

import com.responsi.gamescope.data.model.GameDetailResponse
import com.responsi.gamescope.data.model.GameResponse
import com.responsi.gamescope.util.GameConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String,
        @Query("search") search: String? = null,
        @Query("page_size") pageSize: Int = 20,
        @Query("page") page: Int = 1
    ): GameResponse

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Int,
        @Query("key") apiKey: String
    ): GameDetailResponse
}

object ApiClient {

    val instance: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}