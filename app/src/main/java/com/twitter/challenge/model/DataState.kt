package com.twitter.challenge.model

sealed class DataState {
    object Loading : DataState()
    data class Error(val errorMessage: String) : DataState()
    data class Success(val tweatherItem: TweatherResponse) : DataState()
}