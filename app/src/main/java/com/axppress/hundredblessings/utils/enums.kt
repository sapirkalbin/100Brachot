package com.axppress.hundredblessings.utils

enum class MorningBlessingsScreenType(val num: Int) {
    MORNING_BLESSING(0),
    PITUM_HAKTORET(1),
    SHMA_ISRAEL(2),
}

enum class FoodBlessingsScreenType(val num: Int) {
    BREAD_BLESSING(0),
    WHEAT_BLESSING(1),
    EVERYTHING_BLESSING(2),
    WINE_BLESSING(3),
    TREE_BLESSING(4),
    GROUND_BLESSING(5),
    BREAD_LAST_BLESSING(6),
    BORE_NEFASHOT(7),
    MAAYAN_SHALOSH(8),
}

enum class SmellBlessingsScreenType(val num: Int) {
    ISBEI_BESAMIM(0),
    ATZEI_BESAMIM(1),
    FRUITS(2),
    MINEI_BESAMIM(3),
    AFARSEMON(4)
}

enum class SightBlessings(val num: Int) {
    LIGHTNINGS(0),
    RAINBOW(1),
    OCEAN(2),
    FRUIT_TREES(3),
    RABBI_ISRAEL(4),
    SMART_IN_THE_GOIM(5),
    ISRAEL_KING(6),
    WORLD_KING(7),
    BATEI_KNESIOT(8),
    ISRAEL_MIRACLES(9),
    FAMILY_MIRACLES(10),
    BERIA_MESHUNA(11),
    A_FRIEND(12),
    GOOD_LOOKING_TREES_AND_PEOPLE(13),
    TOMBS(14),
}

enum class EarBlessing(val num: Int) {
    THUNDERS(0),
    BAD_ANNOUNCEMENT(1),
}

enum class NewThings(val num: Int) {
    MEZUZAH(0),
    DISHES(1),
    RAILING(2),
}

enum class OtherBlessings(val num: Int) {
    DOUGH_PRAYER(0),
    WAY_PRAYER(1),
    SHABBAT_CANDLES(2),
}

enum class SpecialPrayers(val num: Int) {
    TIKKUN_HAKLALI(0),
    RABBI_ISHMAEL(1),
    IGERET_HARAMBAN(2),
    TIKUN_HANFESH(3),
    SHIRAT_HAYAM(4),
    SHIR_HASHIRIM(5),
}

fun getEnumByFragmentName(fragmentName: String): Class<out Enum<*>> {
    return when (fragmentName) {
        "food" -> return FoodBlessingsScreenType::class.java
        else -> {
            MorningBlessingsScreenType::class.java
        }
    }
}
const val BREAD_1 = "ברכת המזון נוסח ספרד"
const val BREAD_2 = "ברכת המזון נוסח עדות המזרח"
const val BREAD_3 = "ברכת המזון נוסח אשכנז"
const val BREAD_4 = "ברכת המזון המקוצרת"