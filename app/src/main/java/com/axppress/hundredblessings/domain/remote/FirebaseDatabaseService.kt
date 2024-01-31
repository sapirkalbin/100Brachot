package com.axppress.hundredblessings.domain.remote

import android.content.Context
import com.axppress.hundredblessings.utils.addMyBlessingLocally
import com.axppress.hundredblessings.utils.getLastDate
import com.axppress.hundredblessings.utils.getNumberOfAllBlessingsToday
import com.axppress.hundredblessings.utils.initializeBlessingsOfToday
import com.axppress.hundredblessings.utils.putLastDate
import com.axppress.hundredblessings.utils.putVolumeInstructionsFlag
import com.axppress.hundredblessings.utils.updateAllBlessingsToday
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FirebaseDatabaseService {
    lateinit var numOfAllPeopleBlessingsToday: MutableStateFlow<Int>
    val valueToday = "numOfBlessingToday_" + SimpleDateFormat(
        "dd-MM-yyyy", Locale.ENGLISH
    ).format(Calendar.getInstance().time)
    private val database: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getBlessingsToday(context: Context) {
        getNumberOfBlessingsFromServer { numOfBlessingsTodayFromServer ->
            context.updateNumberOfAllBlessingsTodayUIAndDB(numOfBlessingsTodayFromServer.toInt())
        }
    }

    private fun updateUI(numOfBlessingsToday: Int?) = CoroutineScope(Dispatchers.IO).launch {
        numOfAllPeopleBlessingsToday.emit(numOfBlessingsToday ?: 0)
    }

    fun initializeMyBlessings(context: Context) {
        numOfAllPeopleBlessingsToday = MutableStateFlow(context.getNumberOfAllBlessingsToday())
        val lastDate = context.getLastDate()
        if (lastDate != valueToday) {
            context.initializeBlessingsOfToday(context)
            context.putLastDate(valueToday)
            context.putVolumeInstructionsFlag(false)
        }
    }

    fun addBlessings(blessings: Int, context: Context) {
        context.addMyBlessingLocally(blessings)

        context.updateNumberOfAllBlessingsTodayUIAndDB(
            numOfAllPeopleBlessingsToday.value + blessings,
        )

        getNumberOfBlessingsFromServer { numOfBlessingsTodayFromServer ->
            updateBlessingsOnServer(numOfBlessingsTodayFromServer, blessings, context)
        }
    }

    private fun updateBlessingsOnServer(
        numOfBlessingsTodayFromServer: Long,
        blessings: Int,
        context: Context,
    ) {
        context.updateNumberOfAllBlessingsTodayUIAndDB(
            (numOfBlessingsTodayFromServer + blessings).toInt(),
        )

        database.collection("dailyBlessingCounter")
            .document(valueToday)
            .update(
                mapOf(
                    "counter" to numOfBlessingsTodayFromServer + blessings
                )
            ).continueWith {
                print("Success")
            }.addOnFailureListener { exception ->
                print(exception.message)
            }.addOnCanceledListener {
                print("Canceled")
            }.addOnSuccessListener {
                print("Success")
            }
    }

    private fun Context.updateNumberOfAllBlessingsTodayUIAndDB(
        blessings: Int,
    ) {
        updateUI(blessings)
        updateDB(blessings)
    }

    private fun Context.updateDB(
        numOfBlessingsTodayFromServer: Int,
    ) {
        updateAllBlessingsToday(numOfBlessingsTodayFromServer)
    }

    private fun getNumberOfBlessingsFromServer(onSuccessListener: (Long) -> Unit) {
        database.collection("dailyBlessingCounter").document(valueToday)
            .get().continueWith {
                onSuccessListener(it.result?.get("counter") as Long)
            }.addOnFailureListener {
                print(it.message)
            }.addOnCanceledListener {
                print("Canceled")
            }.addOnSuccessListener {
                print("Success")
            }
    }

    companion object {
        val instance: FirebaseDatabaseService by lazy { FirebaseDatabaseService() }
    }
}