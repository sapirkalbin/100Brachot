package com.axppress.hundredblessings.utils

const val MAIN_FRAGMENT = "main"
const val GENERAL_FRAGMENT = "general"

const val FRAGMENT_0 = "smell" //5
const val FRAGMENT_1 = "ear" // 2
const val FRAGMENT_2 = "food" //9
const val FRAGMENT_3 = "sight" //15
const val FRAGMENT_4 = "morning" //1
const val FRAGMENT_5 = "night" //1
const val FRAGMENT_6 = "other" //3
const val FRAGMENT_7 = "tehilim"
const val FRAGMENT_8 = "specials"
const val FRAGMENT_9  = "news" //6

const val BLESSING_FRAGMENT = "blessing"
const val BLESSING_HTML_FRAGMENT = "blessing_html"


fun getFragmentNameByNum(num: Int) = when (num) {
    0 -> FRAGMENT_0
    1 -> FRAGMENT_1
    2 -> FRAGMENT_2
    3 -> FRAGMENT_3
    4 -> FRAGMENT_4
    5 -> FRAGMENT_5
    6 -> FRAGMENT_6
    7 -> FRAGMENT_7
    8 -> FRAGMENT_8
    else -> MAIN_FRAGMENT
}
