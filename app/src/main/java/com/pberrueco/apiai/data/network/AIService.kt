package com.pberrueco.apiai.data.network

import com.pberrueco.apiai.data.network.model.ScoreResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AIService {

    @POST("questionai")
    suspend fun getResponse(
        @Body questionBody: QuestionBody
    ): Response<List<String>>

    @GET("score")
    suspend fun getScores(): Response<List<ScoreResponse>>

    @PUT("score/{id}")
    suspend fun updateScore(
        @Path("id") id: Int,
        @Body requestBody: Map<String, Long>
    ): Response<Unit>
}
