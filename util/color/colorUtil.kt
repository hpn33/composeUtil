package util.color

import androidx.compose.ui.graphics.Color
import kotlin.random.Random


//val colorMatrix = floatArrayOf(
//    -1f, 0f, 0f, 0f, 255f,
//    0f, -1f, 0f, 0f, 255f,
//    0f, 0f, -1f, 0f, 255f,
//    0f, 0f, 0f, 1f, 0f
//)
//
//fun invertFilter(): ColorFilter {
//
//    return ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
//}

fun getRandomColor() =
    Color(
        Random.nextDouble(1.0).toFloat(),
        Random.nextDouble(1.0).toFloat(),
        Random.nextDouble(1.0).toFloat()
    )

fun Color.invert() =
    copy(red = 1 - red, green = 1 - green, blue = 1 - blue)

fun Color.eyeContrast() =
    if (red * 0.299 + green * 0.587 + blue * 0.114 > 186f)
        Color.Black
    else
        Color.White
