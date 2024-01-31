package com.axppress.hundredblessings.utils

import android.content.Context
import android.content.SharedPreferences
import com.axppress.hundredblessings.domain.remote.FirebaseDatabaseService

fun Context.getSharedPreferences(): SharedPreferences {
    return getSharedPreferences("SharedPreferencesManager", Context.MODE_PRIVATE)
}

fun Context.putBoolean(key: String, value: Boolean) {
    getSharedPreferences().edit().putBoolean(key, value).apply()
}

fun Context.putInt(key: String, value: Int) {
    getSharedPreferences().edit().putInt(key, value).apply()
}

fun Context.putLastDate(value: String) {
    getSharedPreferences().edit().putString(LAST_DATE, value).apply()
}

fun Context.putVolumeInstructionsFlag(value: Boolean) {
    getSharedPreferences().edit().putBoolean(VOLUME_INSTRUCTIONS_FLAG, value).apply()
}

fun Context.getLastDate() = getString(LAST_DATE)
fun Context.getVolumeInstructionsFlag() = getBoolean(VOLUME_INSTRUCTIONS_FLAG)


fun Context.getInt(key: String) = getSharedPreferences().getInt(key, 0)
fun Context.getString(key: String) = getSharedPreferences().getString(key, "")
fun Context.getBoolean(key: String) = getSharedPreferences().getBoolean(key, false)

fun Context.getNumberOfMyBlessingsToday() =
    if (FirebaseDatabaseService.instance.valueToday == getLastDate())
        getInt(NUM_OF_MY_BLESSINGS_TODAY)
    else
        0

fun Context.addMyBlessingLocally(numOfBlessings: Int) {
    val numOfBlessingsToday = getInt(NUM_OF_MY_BLESSINGS_TODAY)
    putInt(NUM_OF_MY_BLESSINGS_TODAY, numOfBlessingsToday + numOfBlessings)
}

fun Context.getNumberOfAllBlessingsToday() =
    if (FirebaseDatabaseService.instance.valueToday == getLastDate())
        getInt(NUM_OF_ALL_BLESSINGS_TODAY)
    else
        0

fun Context.updateAllBlessingsToday(numOfBlessings: Int) {
    putInt(NUM_OF_ALL_BLESSINGS_TODAY, numOfBlessings)
}

fun Context.initializeBlessingsOfToday(context: Context) {
    val numOfBlessingsToday = getInt(NUM_OF_MY_BLESSINGS_TODAY)
    val numOfBlessingsThisWeek = getInt(NUM_OF_BLESSINGS_THIS_WEEK)

    context.putInt(NUM_OF_MY_BLESSINGS_TODAY, 0)
    context.putInt(NUM_OF_ALL_BLESSINGS_TODAY, 0)
    context.putInt(NUM_OF_BLESSINGS_THIS_WEEK, numOfBlessingsThisWeek + numOfBlessingsToday)
}

fun Context.initializeBlessingsOfThisWeek(context: Context) {
    val numOfBlessingsThisWeek = getInt(NUM_OF_BLESSINGS_THIS_WEEK)
    val numOfBlessingsThisMonth = getInt(NUM_OF_BLESSINGS_THIS_MONTH)

    context.putInt(NUM_OF_BLESSINGS_THIS_WEEK, 0)
    context.putInt(NUM_OF_BLESSINGS_THIS_MONTH, numOfBlessingsThisWeek + numOfBlessingsThisMonth)
}

fun Context.initializeBlessingsOfThisMonth(context: Context) {
    val numOfBlessingsThisMonth = getInt(NUM_OF_BLESSINGS_THIS_MONTH)
    val numOfBlessingsThisYear = getInt(NUM_OF_BLESSINGS_THIS_YEAR)

    context.putInt(NUM_OF_BLESSINGS_THIS_MONTH, 0)
    context.putInt(NUM_OF_BLESSINGS_THIS_YEAR, numOfBlessingsThisYear + numOfBlessingsThisMonth)
}

fun Context.initializeBlessingsOfThisYear(context: Context) {
    val numOfBlessingsThisYear = getInt(NUM_OF_BLESSINGS_THIS_YEAR)
    val numOfBlessingsEver = getInt(NUM_OF_BLESSINGS_EVER)

    context.putInt(NUM_OF_BLESSINGS_THIS_YEAR, 0)
    context.putInt(NUM_OF_BLESSINGS_EVER, numOfBlessingsThisYear + numOfBlessingsEver)
}

const val NUM_OF_MY_BLESSINGS_TODAY = "NUM_OF_MY_BLESSINGS_TODAY"
const val NUM_OF_ALL_BLESSINGS_TODAY = "NUM_OF_ALL_BLESSINGS_TODAY"
const val VOLUME_INSTRUCTIONS_FLAG = "VOLUME_INSTRUCTIONS_FLAG"
const val LAST_DATE = "LAST_DATE"


const val NUM_OF_BLESSINGS_THIS_WEEK = "NUM_OF_BLESSINGS_THIS_WEEK"
const val NUM_OF_BLESSINGS_THIS_MONTH = "NUM_OF_BLESSINGS_THIS_MONTH"
const val NUM_OF_BLESSINGS_THIS_YEAR = "NUM_OF_BLESSINGS_THIS_YEAR"
const val NUM_OF_BLESSINGS_EVER = "NUM_OF_BLESSINGS_EVER"
