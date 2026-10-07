package com.responsi.gamescope.data.repository

import com.responsi.gamescope.data.model.GameDetailResponse
import com.responsi.gamescope.data.model.GameItem
import com.responsi.gamescope.network.ApiClient
import com.responsi.gamescope.network.ApiInterface
import com.responsi.gamescope.util.GameConstants

interface GameRepository {
    suspend fun getGames(search: String? = null): Result<List<GameItem>>
    suspend fun getGameDetail(gameId: Int): Result<GameDetailResponse>
}

class GameRepositoryImpl(
    private val api: ApiInterface = ApiClient.instance
) : GameRepository {

    override suspend fun getGames(search: String?): Result<List<GameItem>> {
        return try {
            val response = api.getGames(
                apiKey = GameConstants.RAWG_API_KEY,
                search = if (search.isNullOrBlank()) null else search.trim(),
                pageSize = 30
            )
            Result.success(response.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getGameDetail(gameId: Int): Result<GameDetailResponse> {
        return try {
            val response = api.getGameDetail(
                id = gameId,
                apiKey = GameConstants.RAWG_API_KEY
            )
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
