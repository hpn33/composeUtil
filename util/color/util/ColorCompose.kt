package util.color.util

import androidx.compose.ui.graphics.Color


fun ColorInt.toComposeColor() =
    Color(this.value)

fun Color.toColorInt(): ColorInt {
    val value = argbToInt(
        (alpha * 255).toInt(),
        (red * 255).toInt(),
        (green * 255).toInt(),
        (blue * 255).toInt(),
    )

    return ColorInt(value)
}


fun Int.toColorInt() = ColorInt(this)

