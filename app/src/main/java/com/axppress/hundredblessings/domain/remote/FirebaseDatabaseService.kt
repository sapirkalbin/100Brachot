package com.axppress.hundredblessings.domain.remote

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.axppress.hundredblessings.utils.addBlessingLocally
import com.axppress.hundredblessings.utils.getLastDate
import com.axppress.hundredblessings.utils.initializeBlessingsOfToday
import com.axppress.hundredblessings.utils.putLastDate
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FirebaseDatabaseService {
    val numOfBlessings: MutableStateFlow<Int> = MutableStateFlow(0)
    val valueToday = "numOfBlessingToday_" + SimpleDateFormat(
        "dd-MM-yyyy", Locale.ENGLISH
    ).format(Calendar.getInstance().time)
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun getBlessingsToday() {
        val ref: DatabaseReference = database.getReference(valueToday)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val numOfBlessingsToday: Int? = dataSnapshot.getValue<Int>()
                println(numOfBlessingsToday)
                CoroutineScope(Dispatchers.IO).launch {
                    numOfBlessings.emit(numOfBlessingsToday ?: 0)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
            }
        })
    }

    fun initializeMyBlessings(context: Context) {
        val lastDate = context.getLastDate()
        if (lastDate != valueToday) {
            context.initializeBlessingsOfToday(context)
            context.putLastDate(valueToday)
        }
    }

    fun addBlessings(blessings: Int, context: Context) {
        val ref: DatabaseReference = database.getReference(valueToday)
        numOfBlessings.value.let { numOfBlessingsFromFB ->
            ref.setValue(numOfBlessingsFromFB + blessings)
            .addOnCompleteListener {
                context.addBlessingLocally(blessings)
            }.addOnFailureListener {
                print(it.message)
            }
        }
    }

    companion object {
        val instance: FirebaseDatabaseService by lazy { FirebaseDatabaseService() }
    }
}