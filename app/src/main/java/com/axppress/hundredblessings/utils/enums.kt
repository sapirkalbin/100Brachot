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
    SHMONE_ESRA(0),
    ISRAEL_MIRACLES(1),
    FAMILY_MIRACLES(2),
    HEALTHIER(3),
    DOUGH_PRAYER(4),
    WAY_PRAYER(5),
    ASHER_YATZAR(6),
}

enum class SpecialPrayers(val num: Int) {
    TIKKUN_HAKLALI(0),
    RABBI_ISHMAEL(1),
    IGERET_HARAMBAN(2),
    TIKUN_HANFESH(3),
    SHIRAT_HAYAM(4),
    SHIR_HASHIRIM(5),
}

enum class Tehilim(val num: Int) {
    PEREK_0(0),
    PEREK_1(1),
    PEREK_2(2),
    PEREK_3(3),
    PEREK_4(4),
    PEREK_5(5),
    PEREK_6(6),
    PEREK_7(7),
    PEREK_8(8),
    PEREK_9(9),
    PEREK_10(10),
    PEREK_11(11),
    PEREK_12(12),
    PEREK_13(13),
    PEREK_14(14),
    PEREK_15(15),
    PEREK_16(16),
    PEREK_17(17),
    PEREK_18(18),
    PEREK_19(19),
    PEREK_20(20),
    PEREK_21(21),
    PEREK_22(22),
    PEREK_23(23),
    PEREK_24(24),
    PEREK_25(25),
    PEREK_26(26),
    PEREK_27(27),
    PEREK_28(28),
    PEREK_29(29),
    PEREK_30(30),
    PEREK_31(31),
    PEREK_32(32),
    PEREK_33(33),
    PEREK_34(34),
    PEREK_35(35),
    PEREK_36(36),
    PEREK_37(37),
    PEREK_38(38),
    PEREK_39(39),
    PEREK_40(40),
    PEREK_41(41),
    PEREK_42(42),
    PEREK_43(43),
    PEREK_44(44),
    PEREK_45(45),
    PEREK_46(46),
    PEREK_47(47),
    PEREK_48(48),
    PEREK_49(49),
    PEREK_50(50),
    PEREK_51(51),
    PEREK_52(52),
    PEREK_53(53),
    PEREK_54(54),
    PEREK_55(55),
    PEREK_56(56),
    PEREK_57(57),
    PEREK_58(58),
    PEREK_59(59),
    PEREK_60(60),
    PEREK_61(61),
    PEREK_62(62),
    PEREK_63(63),
    PEREK_64(64),
    PEREK_65(65),
    PEREK_66(66),
    PEREK_67(67),
    PEREK_68(68),
    PEREK_69(69),
    PEREK_70(70),
    PEREK_71(71),
    PEREK_72(72),
    PEREK_73(73),
    PEREK_74(74),
    PEREK_75(75),
    PEREK_76(76),
    PEREK_77(77),
    PEREK_78(78),
    PEREK_79(79),
    PEREK_80(80),
    PEREK_81(81),
    PEREK_82(82),
    PEREK_83(83),
    PEREK_84(84),
    PEREK_85(85),
    PEREK_86(86),
    PEREK_87(87),
    PEREK_88(88),
    PEREK_89(89),
    PEREK_90(90),
    PEREK_91(91),
    PEREK_92(92),
    PEREK_93(93),
    PEREK_94(94),
    PEREK_95(95),
    PEREK_96(96),
    PEREK_97(97),
    PEREK_98(98),
    PEREK_99(99),
    PEREK_100(100),
    PEREK_101(101),
    PEREK_102(102),
    PEREK_103(103),
    PEREK_104(104),
    PEREK_105(105),
    PEREK_106(106),
    PEREK_107(107),
    PEREK_108(108),
    PEREK_109(109),
    PEREK_110(110),
    PEREK_111(111),
    PEREK_112(112),
    PEREK_113(113),
    PEREK_114(114),
    PEREK_115(115),
    PEREK_116(116),
    PEREK_117(117),
    PEREK_118(118),
    PEREK_119(119),
    PEREK_120(120),
    PEREK_121(121),
    PEREK_122(122),
    PEREK_123(123),
    PEREK_124(124),
    PEREK_125(125),
    PEREK_126(126),
    PEREK_127(127),
    PEREK_128(128),
    PEREK_129(129),
    PEREK_130(130),
    PEREK_131(131),
    PEREK_132(132),
    PEREK_133(133),
    PEREK_134(134),
    PEREK_135(135),
    PEREK_136(136),
    PEREK_137(137),
    PEREK_138(138),
    PEREK_139(139),
    PEREK_140(140),
    PEREK_141(141),
    PEREK_142(142),
    PEREK_143(143),
    PEREK_144(144),
    PEREK_145(145),
    PEREK_146(146),
    PEREK_147(147),
    PEREK_148(148),
    PEREK_149(149)
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
        FRAGMENT_7 -> return Tehilim::class.java
        FRAGMENT_8 -> return SpecialPrayers::class.java
        FRAGMENT_9 -> return NewThings::class.java
        else -> return MorningBlessingsScreenType::class.java
    }
}

const val BREAD_1 = "ברכת המזון נוסח ספרד"
const val BREAD_2 = "ברכת המזון נוסח עדות המזרח"
const val BREAD_3 = "ברכת המזון נוסח אשכנז"
const val BREAD_4 = "ברכת המזון המקוצרת"