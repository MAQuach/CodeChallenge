package com.twitter.challenge.di

import com.twitter.challenge.domain.TweatherRepositoryImpl
import com.twitter.challenge.domain.TweatherUseCase
import com.twitter.challenge.network.ApiClient
import com.twitter.challenge.network.ApiService

object DepInj {
    val useCase by lazy { provideTweatherUseCase() }
    private fun provideTweatherUseCase() = TweatherUseCase(provideTweatherRepo())
    private fun provideTweatherRepo() = TweatherRepositoryImpl(provideTweatherService())
    private fun provideTweatherService() = ApiClient.getRetrofit().create(ApiService::class.java)
}