package com.twitter.challenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//get retrofit instance for the api call
object ApiClient {
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://twitter-code-challenge.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}