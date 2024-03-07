package com.axppress.hundredblessings.utils

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.Continuation

object Utils {


    suspend inline fun <T> suspendCoroutineWithTimeout(
        timeout: Long, crossinline block: (Continuation<T>) -> Unit,
    ): T? {
        var finalValue: T? = null
        withTimeoutOrNull(timeout) {
            finalValue = suspendCancellableCoroutine(block = block)
        }
        return finalValue
    }

    fun getBlessingNum(blessingName: String, blessingNum: Int): Int {
        when (blessingName) {
            FRAGMENT_0 -> {
                return 1
            }

            FRAGMENT_1 -> {
                return 1
            }

            FRAGMENT_2 -> {
                return if (blessingNum == 63)
                    5
                else if (blessingNum == 7)
                    2
                else if (blessingNum >= 60)
                    10
                else
                    1
            }

            FRAGMENT_3 -> {
                return 1
            }

            FRAGMENT_4 -> {
                return if (blessingNum == 0)
                    16
                else
                    0
            }

            FRAGMENT_5 -> {
                return 6
            }

            FRAGMENT_6 -> {
                return if (blessingNum == 0)
                    19
                else
                    1
            }

            FRAGMENT_7 -> {
                return 0
            }

            FRAGMENT_8 -> {
                return 1
            }

            FRAGMENT_9 -> {
                return 1
            }

            else -> return 0
        }
    }
}