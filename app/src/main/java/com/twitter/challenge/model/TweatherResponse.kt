package com.twitter.challenge.model

data class TweatherResponse(
    val weather: Weather,
    val wind: Wind,
    val clouds: Clouds,
    val name: String
)