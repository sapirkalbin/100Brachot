package com.axppress.hundredblessings.ui.fragment.mainfragment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class KeysViewModel : ViewModel() {
    private val volume: MutableStateFlow<Int> = MutableStateFlow(24)

    fun volumeUp() {
        CoroutineScope(Dispatchers.IO).launch {
            if (volume.value < 46)
                volume.emit(volume.value + 10)
        }
    }

    fun volumeDown() {
        CoroutineScope(Dispatchers.IO).launch {
            if (volume.value > 24)
                volume.emit(volume.value - 10)
        }
    }

    fun getVolume(): MutableStateFlow<Int> {
        return volume
    }
}

