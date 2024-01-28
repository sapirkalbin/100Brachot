package com.axppress.hundredblessings.ui.fragment.mainfragment

import androidx.lifecycle.ViewModel
import com.axppress.hundredblessings.utils.BREAD_1
import com.axppress.hundredblessings.utils.BREAD_2
import com.axppress.hundredblessings.utils.BREAD_3
import com.axppress.hundredblessings.utils.BREAD_4
import com.axppress.hundredblessings.utils.FoodBlessingsScreenType

class MainViewModel : ViewModel() {
    private var current_fragment: String = "food"
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

    private fun setCurrentBlessingNum(num: Int) {
        current_blessing_num = num
    }

    fun onBlessingClicked(num: Int) {
        setCurrentBlessingNum(num)
    }

    fun onCategoryClicked(name: String) {
        setCurrentFragment(name)
    }

    fun getBlessingListButtons(): List<String> {
        if (current_fragment == "food") {
            when (current_blessing_num) {
                FoodBlessingsScreenType.BREAD_LAST_BLESSING.num -> return listOf(
                    BREAD_1,
                    BREAD_2,
                    BREAD_3,
                    BREAD_4
                )
            }
        } /*else if (current_fragment == "morning") {
            return listOf("shelter", "clothing", "hygiene")
        }*/
        return emptyList()
    }
}

