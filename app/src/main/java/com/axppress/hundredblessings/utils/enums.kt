package com.axppress.hundredblessings.utils

enum class MorningBlessingsScreenType(val num: Int) {
    MORNING_BLESSING(0),
    PITUM_HAKTORET(1),
    SHMA_ISRAEL(2),
}

enum class FoodBlessingsScreenType(val num: Int) {
    BREAD_BLESSING(0),
    WINE_BLESSING(1),
    TREE_BLESSING(2),
    GROUND_BLESSING(3),
    EVERYTHING_BLESSING(4),
}

enum class SmellBlessingsScreenType(val num: Int) {
    BREAD_BLESSING(0),
    MEZONOT_BLESSING(1),
    EVERYTHING_BLESSING(2),
    WINE_BLESSING(3),
    TREE_BLESSING(4),
    GROUND_BLESSING(5),
}


fun getEnumByFragmentName(fragmentName: String): Class<out Enum<*>> {
    return when (fragmentName) {
        "food" -> return FoodBlessingsScreenType::class.java
        else -> {
            MorningBlessingsScreenType::class.java
        }
    }
}