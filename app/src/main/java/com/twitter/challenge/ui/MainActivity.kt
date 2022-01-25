package com.twitter.challenge.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.twitter.challenge.R
import com.twitter.challenge.databinding.ActivityMainBinding
import com.twitter.challenge.di.DepInj
import com.twitter.challenge.getSD
import com.twitter.challenge.model.DataState
import com.twitter.challenge.presentation.TweatherViewModel
import com.twitter.challenge.utils.TemperatureConverter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val tweatherViewModel by lazy {
        ViewModelProvider(viewModelStore, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TweatherViewModel(DepInj.useCase) as T
            }
        })[TweatherViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tweatherViewModel.getCurrentTweather()

        binding.fetchFuture.setOnClickListener {
            tweatherViewModel.getFutureTweather()
        }
        configureObservers()
    }

    private fun configureObservers() {
        tweatherViewModel.currentDataState.observe(this, {
            when (it) {
                is DataState.Success -> {
                    binding.apply {
                        temperature.text =
                            resources.getString(
                                R.string.temperature,
                                it.tweatherItem.weather.temp,
                                TemperatureConverter.celsiusToFahrenheit(it.tweatherItem.weather.temp)
                            )
                        temperature.visibility = View.VISIBLE
                        windSpeed.text = resources.getString(R.string.wind_speed, it.tweatherItem.wind.speed)
                        progressBar.visibility = View.GONE
                        if (it.tweatherItem.clouds.cloudiness > 50) {
                            veryCloudy.visibility = View.VISIBLE
                        }
                    }
                }
                is DataState.Loading -> {
                    binding.apply {
                        currentText.visibility = View.GONE
                        temperature.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }
                is DataState.Error -> {
                    binding.apply {
                        currentText.text = it.errorMessage
                        currentText.visibility = View.VISIBLE
                        temperature.visibility = View.GONE
                        progressBar.visibility = View.GONE
                    }
                }
            }
        })

        tweatherViewModel.futureDataState.observe(this, { futureList ->
            val sdList = mutableListOf<Float>()
            var showSD = true

            futureList.map { listItem ->
                when(listItem) {
                    is DataState.Success -> {
                        sdList.add(listItem.tweatherItem.weather.temp)
                    }
                    is DataState.Error -> {
                        binding.standardDeviation.text = listItem.errorMessage
                        showSD = false // if any are Error, should not show SD
                    }
                    else -> {
                        binding.standardDeviation.text = resources.getString(R.string.error_text)
                    }
                }
            }
            if (showSD){binding.standardDeviation.text = resources.getString(R.string.sd, getSD(sdList))}
        })
    }
}
