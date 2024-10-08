import util.color.util.argbToInt
import util.color.util.intToARGB
import util.color.util.*

data class ColorARGB(
    val alpha: Int,
    val red: Int,
    val green: Int,
    val blue: Int
) : ColorAble {
    override fun toColorInt(): ColorInt {
        return ColorInt(argbToInt(alpha, red, green, blue))
    }

    override fun toString(): String {
        return "$alpha / $red / $green / $blue"
    }
}

fun ColorInt.toArgb(): ColorARGB {

    val colorTemp = intToARGB(this.value)

    return ColorARGB(
        colorTemp.alpha,
        colorTemp.red,
        colorTemp.green,
        colorTemp.blue,
    )
}

//
fun ColorARGB.toRgb(): ColorRGB = toColorInt().toRgb()

//
//fun ARGBColor.asCmyk(): CMYKColor = asColorInt().asCmyk()
//
//fun ARGBColor.asHex(): HEXColor = asColorInt().asHex()
//
fun ColorARGB.asHsl(): ColorHSL = toColorInt().toHSL()
//
//fun ARGBColor.asHsla(): HSLAColor = asColorInt().asHsla()
//
//fun ARGBColor.asHsv(): HSVColor = asColorInt().asHsv()
//
///**
// * @param value amount to lighten in the range 0...1
// */
//fun ARGBColor.lighten(value: Float): ARGBColor = this.asColorInt().lighten(value).asArgb()
//
///**
// * @param value amount to lighten in the range 0...100
// */
//fun ARGBColor.lighten(value: Int): ARGBColor = this.asColorInt().lighten(value).asArgb()
//
///**
// * @param value amount to darken in the range 0...1
// */
//fun ARGBColor.darken(value: Float): ARGBColor = this.asColorInt().darken(value).asArgb()
//
///**
// * @param value amount to darken in the range 0...100
// */
//fun ARGBColor.darken(value: Int): ARGBColor = this.asColorInt().darken(value).asArgb()
//
///**
// * @return a list of shades for the given color like the ones in https://www.color-hex.com/color/e91e63.
// * Each one of the colors is a ARGBColor.
// *
// * @param count of shades to generate over the source color. It generates 10 by default.
// */
//fun ARGBColor.shades(count: Int = 10): List<ARGBColor> = asColorInt().shades(count).map { it.asArgb() }
//
///**
// * @return a list of tints for the given color like the ones in https://www.color-hex.com/color/e91e63.
// * Each one of the colors is a ARGBColor.
// *
// * @param count of tints to generate over the source color. It generates 10 by default.
// */
//fun ARGBColor.tints(count: Int = 10): List<ARGBColor> = asColorInt().tints(count).map { it.asArgb() }
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The complimentary color stands for the
// * color in the opposite side of the circle, so it's (hue + 180) % 360.
// */
//fun ARGBColor.complimentary(): ARGBColor = asColorInt().complimentary().asArgb()
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The triadic colors stand for 3 colors that
// * compose a perfect triangle (equal sides) over the circle, so they are equally far from each other.
// *
// * Triadic colors for h0 would be (hue + 120) % 360 and (hue + 240) % 360.
// */
//fun ARGBColor.triadic(): Pair<ARGBColor, ARGBColor> =
//    asColorInt().triadic().let { Pair(it.first.asArgb(), it.second.asArgb()) }
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The tetradic colors stand for 4 colors
// * that compose a perfect square (equal sides) over the circle, so they are equally far from each other.
// *
// * Tetradic colors for h0 would be (hue + 90) % 360, (hue + 180) % 360 and (hue + 270) % 360.
// */
//fun ARGBColor.tetradic(): Triple<ARGBColor, ARGBColor, ARGBColor> =
//    asColorInt().tetradic().let { Triple(it.first.asArgb(), it.second.asArgb(), it.third.asArgb()) }
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The analogous colors stand for 3 colors
// * that are together in the circle, separated by 30 degrees each.
// *
// * Analogous colors for h0 would be (hue + 30) % 360 & (hue - 30) % 360.
// */
//fun ARGBColor.analogous(): Pair<ARGBColor, ARGBColor> =
//    asColorInt().analogous().let { Pair(it.first.asArgb(), it.second.asArgb()) }
//
///**
// * Check if a color is dark (convert to XYZ & check Y component)
// */
//// TODO
////fun ARGBColor.isDark(): Boolean = ColorUtils.calculateLuminance(this.asColorInt()) < 0.5
//
///**
// * Returns a color that contrasts nicely with the one given (receiver). Fallbacks to white and
// * black, but optional light and dark colors can be passed.
// */
////fun ARGBColor.contrasting(
////    lightColor: ARGBColor = ARGBColor(255, 255, 255, 255),
////    darkColor: ARGBColor = ARGBColor(255, 0, 0, 0)
////) = if (isDark()) {
////    lightColor
////} else {
////    darkColor
////}
