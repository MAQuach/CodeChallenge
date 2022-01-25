package com.twitter.challenge

import kotlin.math.pow
import kotlin.math.sqrt

fun getSD(array: List<Float>): Float {
    var sum = 0.0
    var sd = 0.0

    for (i in array) {sum += i}

    val mean = sum / 10

    for (j in array) {sd += (j - mean).pow(2.0)}

    return sqrt(sd / 10).toFloat()
}