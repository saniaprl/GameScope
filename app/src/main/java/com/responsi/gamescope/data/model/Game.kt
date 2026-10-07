package com.responsi.gamescope.data.model

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<GameItem>
)

data class GameItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("released")
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("rating_top")
    val ratingTop: Int?,
    @SerializedName("ratings_count")
    val ratingsCount: Int?
)

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class GameDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("released")
    val released: String?,
    @SerializedName("description_raw")
    val descriptionRaw: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("publishers")
    val publishers: List<Publisher>?,
    @SerializedName("platforms")
    val platforms: List<PlatformWrapper>?
)

data class Publisher(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class PlatformWrapper(
    @SerializedName("platform")
    val platform: Platform
)

data class Platform(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
