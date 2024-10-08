package util.color

import kotlin.math.max
import kotlin.math.min


fun clamp(value: Int, min: Int, max: Int): Int =
    max(min, min(max, value))


// --------------------------


object ColorConvert {

    @OptIn(ExperimentalStdlibApi::class)
    fun integerToHex(colorInteger: Int): String {
        return colorInteger.toHexString()

//        return String.format(
//            "#%06X",
//            (0xFFFFFF and colorInteger)
//        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun hexToInteger(hex: String): Int {
        return hex.hexToInt()

//        val colorInt = 0xff000000 or hex.toInt(16).toLong()
//        return hex.toLong(16)
    }
}
