package com.twitter.challenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twitter.challenge.domain.TweatherUseCase
import com.twitter.challenge.model.DataState
import kotlinx.coroutines.launch

class TweatherViewModel(private val useCase: TweatherUseCase) : ViewModel() {

    private val _currentDataState = MutableLiveData<DataState>()
    val currentDataState: LiveData<DataState>
        get() = _currentDataState

    private val _futureDataState = MutableLiveData<List<DataState>>()
    val futureDataState: LiveData<List<DataState>>
        get() = _futureDataState

    fun getCurrentTweather() {
        viewModelScope.launch {
            _currentDataState.postValue(DataState.Loading)
            _currentDataState.postValue(useCase.getCurrentTweather())
        }
    }

    fun getFutureTweather() {
        viewModelScope.launch {
            _futureDataState.postValue(useCase.getFutureTweather())
        }
    }
}