package com.rizalferdian.moviecatalogue.util

import java.text.NumberFormat
import java.util.*

fun Int.toCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale.US).run {
        maximumFractionDigits = 0
        format(this@toCurrency)
    }
}