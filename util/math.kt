package util

fun divideZero(one: Float, two: Float): Float {
    if (one == 0f || two == 0f) {
        return 0f
    }

    return one / two
}

inline fun divideZero(one: Int, two: Int): Float =
    divideZero(one.toFloat(), two.toFloat())


fun clamp(value: Float, min: Float, max: Float): Float {
    if (value < min) {
        return min
    }
    if (value > max) {
        return max
    }
    return value
}