package com.axppress.hundredblessings.utils
/*"food" -> return 9
"sight" -> return 15
"smell" -> return 5
"ear" -> return 2
"morning" -> return 1
"night" -> return 1
"other" -> return 3
"news" -> return 6*/


enum class MorningBlessingsScreenType(val num: Int) {
    MORNING_BLESSING(0),
    PITUM_HAKTORET(1),
    SHMA_ISRAEL(2),
    SGULA(3),
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
    THUNDER(1),
    RAINBOW(2),
    OCEAN(3),
    FRUIT_TREES(4),
    RABBI_ISRAEL(5),
    SMART_IN_THE_GOIM(6),
    ISRAEL_KING(7),
    WORLD_KING(8),
    BERIA_MESHUNA(9),
    GOOD_LOOKING_TREES_AND_PEOPLE(10),
    TOMBS(11),
    FIRE_BLESSING(12),
    RAZIM(13),
    A_FRIEND(14),
}

enum class EarBlessing(val num: Int) {
    THUNDERS_AND_NATURE(0),
    BAD_ANNOUNCEMENT(1),
}

enum class NewThings(val num: Int) {
    RAILING_FOR_HIMSELF(0),
    RAILING_FOR_OTHER(1),
    DISHES(2),
    MEZUZAH(3),
    BEIT_KNESET(4),
    NEW_THINGS(5),
}

enum class OtherBlessings(val num: Int) {
    ISRAEL_MIRACLES(0),
    FAMILY_MIRACLES(1),
    HEALTHIER(2),
    DOUGH_PRAYER(3),
    WAY_PRAYER(4),
    ASHER_YATZAR(5),
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
        FRAGMENT_0 -> return SmellBlessingsScreenType::class.java
        FRAGMENT_1 -> return EarBlessing::class.java
        FRAGMENT_2 -> return FoodBlessingsScreenType::class.java
        FRAGMENT_3 -> return SightBlessings::class.java
        FRAGMENT_4 -> return MorningBlessingsScreenType::class.java
        FRAGMENT_5 -> return MorningBlessingsScreenType::class.java
        FRAGMENT_6 -> return OtherBlessings::class.java
        FRAGMENT_7 -> return SpecialPrayers::class.java
        FRAGMENT_8 -> return SpecialPrayers::class.java
        FRAGMENT_9 -> return NewThings::class.java
        else -> return MorningBlessingsScreenType::class.java
    }
}

const val BREAD_1 = "ברכת המזון נוסח ספרד"
const val BREAD_2 = "ברכת המזון נוסח עדות המזרח"
const val BREAD_3 = "ברכת המזון נוסח אשכנז"
const val BREAD_4 = "ברכת המזון המקוצרת"