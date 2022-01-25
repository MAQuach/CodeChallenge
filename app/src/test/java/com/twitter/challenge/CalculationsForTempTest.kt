package com.twitter.challenge

import com.twitter.challenge.utils.TemperatureConverter.celsiusToFahrenheit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Java6Assertions
import org.junit.Test

class CalculationsForTempTest {

    @Test
    fun testCelsiusToFahrenheitConversion() {
        val precision = Java6Assertions.within(0.01f)
        assertThat(celsiusToFahrenheit(-50f)).isEqualTo(-58f, precision)
        assertThat(celsiusToFahrenheit(0f)).isEqualTo(32f, precision)
        assertThat(celsiusToFahrenheit(10f)).isEqualTo(50f, precision)
        assertThat(celsiusToFahrenheit(21.11f)).isEqualTo(70f, precision)
        assertThat(celsiusToFahrenheit(37.78f)).isEqualTo(100f, precision)
        assertThat(celsiusToFahrenheit(100f)).isEqualTo(212f, precision)
        assertThat(celsiusToFahrenheit(1000f)).isEqualTo(1832f, precision)
    }

    @Test
    fun testGetSD() {
        val floatList = listOf(16.83f, 11.15f, 14.20f, 9.88f, 19.19f) // temps from api call

        assertThat(getSD(floatList).equals(5.599978)) // SD == 5.599978 || 5.60
    }
}