package com.pberrueco.apiai.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AIService {

    @POST("questionai")
    suspend fun askQuestion(
        @Header("Authorization") userName: String,
        @Body questionBody: QuestionBody
    ): Response<List<String>>
}