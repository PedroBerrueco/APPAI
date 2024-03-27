package com.pberrueco.apiai.data.network.model


import com.google.gson.annotations.SerializedName

data class ScoreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("media")
    val media: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("points")
    val points: Int,
    @SerializedName("votes")
    val votes: Int
)