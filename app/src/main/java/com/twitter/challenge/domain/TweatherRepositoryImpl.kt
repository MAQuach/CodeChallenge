package com.twitter.challenge.domain

import com.twitter.challenge.model.DataState
import com.twitter.challenge.network.ApiService


class TweatherRepositoryImpl(
    private val apiService: ApiService
) : TweatherRepository {
    override suspend fun getCurrentTweather(): DataState {
        val response = apiService.getCurrentWeather()
        return if (response.isSuccessful) {
            DataState.Success(response.body()!!)
        } else {
            DataState.Error("Error loading data")
        }
    }

    override suspend fun getFutureTweather(): List<DataState> =
        listOf(
            getFutureItem(1),
            getFutureItem(2),
            getFutureItem(3),
            getFutureItem(4),
            getFutureItem(5)
        )

    private suspend fun getFutureItem(num: Int): DataState {
        val response = apiService.getFutureWeather(num)
        return if (response.isSuccessful) {
            DataState.Success(response.body()!!)
        } else {
            DataState.Error("Error loading data")
        }
    }
}