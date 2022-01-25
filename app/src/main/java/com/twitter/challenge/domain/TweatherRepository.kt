package com.twitter.challenge.domain

import com.twitter.challenge.model.DataState

interface TweatherRepository {
    suspend fun getCurrentTweather(): DataState
    suspend fun getFutureTweather(): List<DataState>
}