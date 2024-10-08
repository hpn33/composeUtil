package util.color.util

import androidx.compose.ui.graphics.Color
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


// rgb to hsl
/**
 * Convert RGB components to HSL (hue-saturation-lightness).
 * <ul>
 * <li>outHsl[0] is Hue [0, 360)</li>
 * <li>outHsl[1] is Saturation [0, 1]</li>
 * <li>outHsl[2] is Lightness [0, 1]</li>
 * </ul>
 *
 * @param r      red component value [0, 255]
 * @param g      green component value [0, 255]
 * @param b      blue component value [0, 255]
 * @param outHsl 3-element array which holds the resulting HSL components
 */
fun rgbToHsl(color: Color) =
    rgbToHsl(
        (color.red * 255).toInt(),
        (color.green * 255).toInt(),
        (color.blue * 255).toInt()
    )

//fun Color.toHSL() = rgbToHsl(this)

fun rgbToHsl(
    r: Int,
    g: Int,
    b: Int,
//    float[] outHsl
): List<Float> {
    val rf = r / 255f;
    val gf = g / 255f;
    val bf = b / 255f;

    val max = max(rf, Math.max(gf, bf));
    val min = min(rf, Math.min(gf, bf));
    val deltaMaxMin = max - min;

    var h = 0f
    var s = 0f;
    val l = (max + min) / 2f;

    if (max != min) {
        h = when (max) {
            rf -> ((gf - bf) / deltaMaxMin) % 6f;
            gf -> ((bf - rf) / deltaMaxMin) + 2f;
            else -> ((rf - gf) / deltaMaxMin) + 4f;

        }

        s = deltaMaxMin / (1f - abs(2f * l - 1f));
    }

    h = (h * 60f) % 360f;
    if (h < 0) {
        h += 360f;
    }

    return listOf(h, s, l)
}

// hsl to rgb

fun hslToRgb(h: Float, s: Float, l: Float): ColorRGB {

    val c = ((1f - abs((2 * l - 1f).toDouble())) * s).toFloat()
    val m = l - 0.5f * c
    val x = (c * (1f - abs(((h / 60f % 2f) - 1f).toDouble()))).toFloat()

    val hueSegment = h.toInt() / 60

    var r = 0
    var g = 0
    var b = 0

    when (hueSegment) {
        0 -> {
            r = Math.round(255 * (c + m))
            g = Math.round(255 * (x + m))
            b = Math.round(255 * m)
        }

        1 -> {
            r = Math.round(255 * (x + m))
            g = Math.round(255 * (c + m))
            b = Math.round(255 * m)
        }

        2 -> {
            r = Math.round(255 * m)
            g = Math.round(255 * (c + m))
            b = Math.round(255 * (x + m))
        }

        3 -> {
            r = Math.round(255 * m)
            g = Math.round(255 * (x + m))
            b = Math.round(255 * (c + m))
        }

        4 -> {
            r = Math.round(255 * (x + m))
            g = Math.round(255 * m)
            b = Math.round(255 * (c + m))
        }

        5, 6 -> {
            r = Math.round(255 * (c + m))
            g = Math.round(255 * m)
            b = Math.round(255 * (x + m))
        }
    }

//    r = constrain(r, 0, 255)
//    g = constrain(g, 0, 255)
//    b = constrain(b, 0, 255)
    return ColorRGB(r, g, b)

//    return android.graphics.Color.rgb(r, g, b)
}