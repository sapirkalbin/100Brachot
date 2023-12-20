package com.axppress.hundredblessings.utils

object TextFormatter {
    fun numberWithCommas(number: Int): String {
        return String.format("%,d", number)
    }
}
