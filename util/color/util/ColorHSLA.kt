package util.color.util//package util.color.jorgecastillo
//
//
//import util.color.jorgecastillo.*
//import kotlin.math.roundToInt
//
///**
// * HSLA stands for hue-saturation-lightness-alpha.
// *
// * Hue is a value from 0...360
// * Saturation is a value from 0...1
// * Lightness is a value from 0...1
// * Alpha is a value from 0...1
// */
//data class HSLAColor(
//    val hue: Float,
//    val saturation: Float,
//    val lightness: Float,
//    val alpha: Float
//) {
//    override fun toString(): String {
//        return "${hue}º / $saturation / $lightness / $alpha"
//    }
//}
//
//
//fun ColorInt.asHsla(): HSLAColor = this.let { color ->
//    FloatArray(3).apply { ColorUtils.colorToHSL(color, this) }.let {
//        HSLAColor(it[0], it[1], it[2], Color.alpha(color) / 255f)
//    }
//}
//
//fun HSLAColor.asFloatArray(): FloatArray = floatArrayOf(hue, saturation, lightness, alpha)
//
//
//fun HSLAColor.asColorInt(): ColorInt =
//    HSLToColor(asFloatArray().dropLast(1).toFloatArray()).let {
//        Color.argb((alpha * 255).toInt(), Color.red(it), Color.green(it), Color.blue(it))
//    }
//
//fun HSLAColor.asRgb(): RGBColor = asColorInt().asRgb()
//
//fun HSLAColor.asArgb(): ARGBColor = asColorInt().asArgb()
//
//fun HSLAColor.asCmyk(): CMYKColor = asHsl().asCmyk()
//
//fun HSLAColor.asHex(): HEXColor = asColorInt().asHex()
//
//fun HSLAColor.asHsl(): HSLColor = HSLColor(hue, saturation, lightness)
//
//fun HSLAColor.asHsv(): HSVColor = asColorInt().asHsv()
//
///**
// * @param value amount to lighten in the range 0...1
// */
//fun HSLAColor.lighten(value: Float): HSLAColor {
//    var mutableLightness = this.lightness
//    mutableLightness += value
//    mutableLightness = 0f.coerceAtLeast(mutableLightness.coerceAtMost(1f))
//    return HSLAColor(hue, saturation, mutableLightness, alpha)
//}
//
///**
// * @param value amount to lighten in the range 0...100
// */
//fun HSLAColor.lighten(value: Int): HSLAColor {
//    var mutableLightness = this.lightness
//    mutableLightness += value / 100f
//    mutableLightness = 0f.coerceAtLeast(mutableLightness.coerceAtMost(1f))
//    return HSLAColor(hue, saturation, mutableLightness, alpha)
//}
//
///**
// * @param value amount to darken in the range 0...1
// */
//fun HSLAColor.darken(value: Float): HSLAColor {
//    var mutableLightness = this.lightness
//    mutableLightness -= value
//    mutableLightness = 0f.coerceAtLeast(mutableLightness.coerceAtMost(1f))
//    return HSLAColor(hue, saturation, mutableLightness, alpha)
//}
//
///**
// * @param value amount to darken in the range 0...100
// */
//fun HSLAColor.darken(value: Int): HSLAColor {
//    var mutableLightness = this.lightness
//    mutableLightness -= value / 100f
//    mutableLightness = 0f.coerceAtLeast(mutableLightness.coerceAtMost(1f))
//    return HSLAColor(hue, saturation, mutableLightness, alpha)
//}
//
///**
// * @return a list of shades for the given color like the ones in https://www.color-hex.com/color/e91e63.
// * Each one of the colors is a HSLColor.
// *
// * @param count of shades to generate over the source color. It generates 10 by default.
// */
//fun HSLAColor.shades(count: Int = 10): List<HSLAColor> {
//    require(count > 0) { "count must be > 0" }
//    val start = (this.lightness * 10000000).roundToInt()
//    val step = if (start > 0) {
//        -1 * start / count
//    } else 1
//    return IntProgression.fromClosedRange(start, 0, step).map { i ->
//        this.copy(lightness = i / 10000000f)
//    }
//}
//
///**
// * @return a list of tints for the given color like the ones in https://www.color-hex.com/color/e91e63.
// * Each one of the colors is a HSLColor.
// *
// * @param count of tints to generate over the source color. It generates 10 by default.
// */
//fun HSLAColor.tints(count: Int = 10): List<HSLAColor> {
//    require(count > 0) { "count must be > 0" }
//
//    val start = (this.lightness * 10000000).roundToInt()
//    val step = if (start < 10000000) (10000000 - start) / count else 1
//    return IntProgression.fromClosedRange(start, 10000000, step).map { i ->
//        this.copy(lightness = i / 10000000f)
//    }
//}
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The complimentary color stands for the
// * color in the opposite side of the circle, so it's (hue + 180) % 360.
// */
//fun HSLAColor.complimentary(): HSLAColor {
//    val complimentaryHue = (hue + 180) % 360
//    return this.copy(hue = complimentaryHue)
//}
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°,
// * representing the 360° of the wheel; 0° being red, 180° being red's opposite colour cyan, and so
// * on. The triadic colors stand for 3 colors that compose a perfect triangle (equal sides) over the
// * circle, so they are equally far from each other.
// *
// * Triadic colors for h0 would be (hue + 120) % 360 and (hue + 240) % 360.
// */
//fun HSLAColor.triadic(): Pair<HSLAColor, HSLAColor> {
//    val h1 = this.copy(hue = (this.hue + 120) % 360f)
//    val h2 = this.copy(hue = (this.hue + 240) % 360f)
//
//    return Pair(h1, h2)
//}
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The tetradic colors stand for 4 colors
// * that compose a perfect square (equal sides) over the circle, so they are equally far from each other.
// *
// * Tetradic colors for h0 would be (hue + 90) % 360, (hue + 180) % 360 and (hue + 270) % 360.
// */
//fun HSLAColor.tetradic(): Triple<HSLAColor, HSLAColor, HSLAColor> {
//    val hue = this.hue // 0° to 359°
//
//    val h1 = this.copy(hue = (hue + 90) % 360)
//    val h2 = this.copy(hue = (hue + 180) % 360)
//    val h3 = this.copy(hue = (hue + 270) % 360)
//
//    return Triple(h1, h2, h3)
//}
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The analogous colors stand for 3 colors
// * that are together in the circle, separated by 30 degrees each.
// *
// * Analogous colors for h0 would be (hue + 30) % 360 & (hue - 30) % 360.
// */
//fun HSLAColor.analogous(): Pair<HSLAColor, HSLAColor> {
//    val h1 = this.copy(hue = (hue + 30) % 360)
//    val h2 = this.copy(hue = (hue + 330) % 360)
//
//    return Pair(h1, h2)
//}
//
///**
// * Check if a color is dark (convert to XYZ & check Y component)
// */
//
//// TODO
////fun HSLAColor.isDark(): Boolean = ColorUtils.calculateLuminance(this.asColorInt()) < 0.5
//
///**
// * Returns a color that contrasts nicely with the one given (receiver). Fallbacks to white and
// * black, but optional light and dark colors can be passed.
// */
////fun HSLAColor.contrasting(
////    lightColor: HSLAColor = HSLAColor(0f, 0f, 1f, 1f), // white
////    darkColor: HSLAColor = HSLAColor(0f, 0f, 0f, 1f) // black
////) = if (isDark()) {
////    lightColor
////} else {
////    darkColor
////}
