package com.pberrueco.apiai.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseApi {
    //Parsea los Json a data class
    private val converter = GsonConverterFactory.create()

    //Asigna el nivel de detalle que queremos por consola de las peticiones
    private val logginIntercerptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //Carga el Interceptor
    private val client = OkHttpClient.Builder().addInterceptor(logginIntercerptor).build()

    //Instancia de Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.106:8080/apiai/v1/") //TODO poner base_url Tiene que terminar siempre en /
        .client(client)
        .addConverterFactory(converter)
        .build()
    //Para Llamar a las peticiones de red del servicio
    val service: AIService by lazy {
        retrofit.create(AIService::class.java)
    }
}