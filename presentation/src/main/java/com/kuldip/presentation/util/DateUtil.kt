package com.kuldip.presentation.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    /**
     * Convert date to string with the given dateFormat.
     */
    fun convertDateToString(date: Date, dateFormat: String): String {
        val sdfFormat = SimpleDateFormat(dateFormat, Locale.ENGLISH)
        return sdfFormat.format(date)
    }
}
