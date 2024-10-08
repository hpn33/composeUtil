package util.color.util

// --------------------------

inline fun rgbToInt(value: ARGBValue): Int =
    rgbToInt(value.red, value.green, value.blue)

inline fun rgbToInt(red: Int, green: Int, blue: Int): Int =
    argbToInt(255, red, green, blue)

inline fun argbToInt(value: ARGBValue): Int =
    argbToInt(value.alpha, value.red, value.green, value.blue)

fun argbToInt(alpha: Int, red: Int, green: Int, blue: Int): Int =
    (alpha shl 24) or (red shl 16) or (green shl 8) or blue

// --------------------------

fun intToARGB(value: Int): ARGBValue {

    val alpha = value shr 24 and 0xFF
    val red = value shr 16 and 0xFF
    val green = value shr 8 and 0xFF
    val blue = value and 0xFF

    return ARGBValue(alpha, red, green, blue)
}


data class ARGBValue(val alpha: Int = 255, val red: Int, val green: Int, val blue: Int)

