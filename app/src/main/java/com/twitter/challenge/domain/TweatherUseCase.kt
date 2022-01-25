package com.twitter.challenge.domain

class TweatherUseCase(private val tweatherRepository: TweatherRepository) {
    suspend fun getCurrentTweather() = tweatherRepository.getCurrentTweather()
    suspend fun getFutureTweather() = tweatherRepository.getFutureTweather()
}