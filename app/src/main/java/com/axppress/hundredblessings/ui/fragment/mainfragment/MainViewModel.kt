package com.axppress.hundredblessings.ui.fragment.mainfragment

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var current_fragment: String = "food_fragment"
    private var current_blessing_num = 0

    fun getCurrentFragment(): String {
        return current_fragment
    }

    fun setCurrentFragment(fragment: String) {
        current_fragment = fragment
    }

    fun getCurrentBlessingNum(): Int {
        return current_blessing_num
    }

    fun setCurrentBlessingNum(num: Int) {
        current_blessing_num = num
    }
}

