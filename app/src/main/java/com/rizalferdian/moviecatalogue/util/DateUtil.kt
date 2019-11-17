package com.rizalferdian.moviecatalogue.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(pattern: String, locale: Locale): String {
    return SimpleDateFormat(pattern, locale).format(this)
}