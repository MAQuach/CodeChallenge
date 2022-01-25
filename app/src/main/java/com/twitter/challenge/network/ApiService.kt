package com.twitter.challenge.network

import com.twitter.challenge.model.TweatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//GET functions for each query needed
interface ApiService {
    @GET("current.json")
    suspend fun getCurrentWeather()
            : Response<TweatherResponse>

    @GET("future_{int}.json")
    suspend fun getFutureWeather(@Path("int") int: Int)
            : Response<TweatherResponse>
}