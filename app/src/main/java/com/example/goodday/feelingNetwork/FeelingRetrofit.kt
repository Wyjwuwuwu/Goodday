package com.example.goodday.feelingNetwork

import com.example.goodday.feelingModel.Feeling
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FeelingRetrofit {
    @GET("feeling/")
    fun getAllFeeling(): Call<List<Feeling>>

    @POST("/feeling")
    suspend fun addFeeling(@Body feeling: Feeling): Response<Feeling>
}